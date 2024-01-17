package com.tbs.personnel.deployment.tracker.service;

import com.tbs.personnel.deployment.tracker.dto.LoginRequestDto;
import com.tbs.personnel.deployment.tracker.model.entities.Commander;
import com.tbs.personnel.deployment.tracker.model.entities.Enlisted;
import com.tbs.personnel.deployment.tracker.model.entities.Token;
import com.tbs.personnel.deployment.tracker.repository.CommanderRepository;
import com.tbs.personnel.deployment.tracker.repository.EnlistedRepository;
import com.tbs.personnel.deployment.tracker.repository.TokenRepository;
import com.tbs.personnel.deployment.tracker.dto.LoginResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.security.auth.message.AuthException;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  @Autowired
  CommanderRepository commanderRepository;

  @Autowired
  EnlistedRepository enlistedRepository;

  @Autowired
  TokenRepository tokenRepository;

  @Value("${token-validity}")
  Long tokenValidity;

  public LoginResponseDto loginAndGenerateToken(LoginRequestDto loginRequestDto) {
    Commander commander = commanderRepository.findCommanderByIdAndPassword(loginRequestDto.getId(), loginRequestDto.getPassword());
    Enlisted enlisted = enlistedRepository.findEnlistedByIdAndPassword(loginRequestDto.getId(), loginRequestDto.getPassword());
    Token alreadyLoggedIn = tokenRepository.findById(loginRequestDto.getId()).orElse(null);
    String token = new String();
    if(commander != null || enlisted != null) {
        token = UUID.randomUUID().toString();
        LocalDateTime validity = LocalDateTime.now().plusSeconds(tokenValidity);
        if(alreadyLoggedIn != null) {
          return new LoginResponseDto(alreadyLoggedIn.getToken());
        } else {
          tokenRepository.save(
                  new Token(
                          loginRequestDto.getId(),
                          token,
                          validity
                  )
          );
        }
      } else {
        throw new EntityNotFoundException("No such credentials, please check ID / Password");
      }
    return new LoginResponseDto(token);
  }

  public Boolean logout(String token) throws AuthException {
    tokenRepository.deleteById(tokenEvaluation(token));
    return true;
  }

  public LoginResponseDto refreshToken(String token) throws AuthException {
    Token tokenToRefresh = tokenRepository.findById(tokenEvaluation(token)).orElseThrow(() -> new EntityNotFoundException("Encountered some problem"));
    tokenToRefresh.setExpirationDate(LocalDateTime.now().plusSeconds(tokenValidity));
    tokenToRefresh.setToken(UUID.randomUUID().toString());
    tokenRepository.save(tokenToRefresh);
    return new LoginResponseDto(tokenToRefresh.getToken());
  }

  public String tokenEvaluation(String token) throws AuthException {
    if(token == null){
      throw new AuthException("There are no Token");
    }
    Token connectedToken = tokenRepository.findByToken(token);
    if(connectedToken != null){
      if(connectedToken.getExpirationDate().isBefore(LocalDateTime.now())){
        tokenRepository.delete(connectedToken);
        throw new AuthException("Token is not valid/Expired");
      }
    }  else {
      throw new AuthException("Token is not valid/Expired");
    }
    return connectedToken.getId();
  }

  public void authorization(String connectedId, String role) throws AuthException {
    Commander connectedCommander = commanderRepository.findById(connectedId).orElseThrow(() -> new EntityNotFoundException());
    if(!connectedCommander.getCommanderRole().equals(role)){
      throw new AuthException("Not Authorized / insufficient role");
    }
  }
}