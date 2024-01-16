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
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  @Autowired
  CommanderRepository commanderRepository;

  @Autowired
  EnlistedRepository enlistedRepository;
  @Autowired
  TokenRepository tokenRepository;

  public LoginResponseDto loginAndGenerateToken(LoginRequestDto loginRequestDto) {
    Commander commander = commanderRepository.findCommanderByIdAndPassword(loginRequestDto.getId(), loginRequestDto.getPassword());
    Enlisted enlisted = enlistedRepository.findEnlistedByIdAndPassword(loginRequestDto.getId(), loginRequestDto.getPassword());
    String token = new String();
    if(commander != null || enlisted != null) {
        token = UUID.randomUUID().toString();
        tokenRepository.save(new Token(loginRequestDto.getId(), token));
      } else {
        throw new EntityNotFoundException("No such credentials, please check ID / Password");
      }
    return new LoginResponseDto(token);
  }

  public Boolean logout(String token) throws AuthException {
    tokenRepository.deleteById(tokenEvaluation(token));
    return true;
  }



  public String tokenEvaluation(String token) throws AuthException {
    if(token == null){
      throw new AuthException("There are no Token");
    }
    Token connectedToken = tokenRepository.findByToken(token);
    if(connectedToken == null){
      throw new AuthException("Token is not valid");
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