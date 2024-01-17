package com.tbs.personnel.deployment.tracker.controller;

import com.tbs.personnel.deployment.tracker.dto.LoginRequestDto;
import com.tbs.personnel.deployment.tracker.dto.LoginResponseDto;
import com.tbs.personnel.deployment.tracker.service.AuthenticationService;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


  @Autowired
  AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest){
    return new ResponseEntity<>(authenticationService.loginAndGenerateToken(loginRequest), HttpStatus.OK);
  }


  @PostMapping("/logout")
  public ResponseEntity<Boolean> login(@RequestHeader(value = "x-token", required = false) String token)
      throws AuthException {
    return new ResponseEntity<>(authenticationService.logout(token), HttpStatus.OK);
  }


}
