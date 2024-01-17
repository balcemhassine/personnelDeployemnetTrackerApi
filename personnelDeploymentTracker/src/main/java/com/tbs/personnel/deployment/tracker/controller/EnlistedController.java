package com.tbs.personnel.deployment.tracker.controller;


import com.tbs.personnel.deployment.tracker.dto.EnlistedDto;
import com.tbs.personnel.deployment.tracker.service.EnlistedService;
import com.tbs.personnel.deployment.tracker.dto.EnrollmentDto;
import com.tbs.personnel.deployment.tracker.service.AuthenticationService;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enlisted")
public class EnlistedController {
    @Autowired
    EnlistedService service;

    @Autowired
    AuthenticationService auth;


    @GetMapping("/")
    public ResponseEntity<List<EnlistedDto>> getAll(@RequestHeader(value = "x-token", required = false) String token) {
        HttpStatus status = HttpStatus.OK;
        List<EnlistedDto> result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result = service.getAll();
        } catch (AuthException e) {
           if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid") ) {
               status  = HttpStatus.UNAUTHORIZED;
           } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
               status  = HttpStatus.FORBIDDEN;
           }
        }
        return new ResponseEntity(result, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnlistedDto> getById(@RequestHeader(value = "x-token", required = false) String token, @PathVariable String id){

        HttpStatus status = HttpStatus.OK;
        EnlistedDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result = service.getById(id);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }

    @PostMapping("/")
    public ResponseEntity<EnlistedDto> create(@RequestHeader(value = "x-token", required = false) String token, @RequestBody EnlistedDto enlistedDto){
        HttpStatus status = HttpStatus.OK;
        EnlistedDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result = service.create(enlistedDto);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EnlistedDto> update(@RequestHeader(value = "x-token", required = false) String token, @PathVariable String id, @RequestBody EnlistedDto enlistedDto){
        HttpStatus status = HttpStatus.OK;
        EnlistedDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result = service.update(id, enlistedDto);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EnlistedDto> patch(@RequestHeader(value = "x-token", required = false) String token, @PathVariable String id, @RequestBody EnlistedDto enlistedDto){
        HttpStatus status = HttpStatus.OK;
        EnlistedDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result = service.patch(id, enlistedDto);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnlistedDto> delete(@RequestHeader(value = "x-token", required = false) String token, @PathVariable String id){
        HttpStatus status = HttpStatus.OK;
        EnlistedDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result = service.delete(id);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }

    @PostMapping("/enroll")
    public ResponseEntity<Boolean> enrollEnlistedToDeployment(@RequestHeader(value = "x-token", required = false) String token, @RequestBody EnrollmentDto enrollmentDto){
        HttpStatus status = HttpStatus.OK;
        Boolean result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result = service.enrollEnlisted(enrollmentDto);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }
}
