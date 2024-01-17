package com.tbs.personnel.deployment.tracker.controller;

import com.tbs.personnel.deployment.tracker.dto.CommanderDto;
import com.tbs.personnel.deployment.tracker.service.AuthenticationService;
import com.tbs.personnel.deployment.tracker.service.CommanderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commander")
public class CommanderController {

    @Autowired
    CommanderService service;

    @Autowired
    AuthenticationService auth;

    @Operation(summary = "Get List of All Commanders")
    @GetMapping("/")
    public ResponseEntity<List<CommanderDto>> getAll(@RequestHeader(value = "x-token", required = false) String token){

        HttpStatus status = HttpStatus.OK;
        List<CommanderDto> result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result =service.getAll();
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }

    @Operation(summary = "Get a Commander by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CommanderDto> getById(@RequestHeader(value = "x-token", required = false) String token, @PathVariable(name = "id") String id){
        HttpStatus status = HttpStatus.OK;
        CommanderDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result =service.getById(id);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }
    @Operation(summary = "Create a new Commander")
    @PostMapping("/")
    public ResponseEntity<CommanderDto> create(@RequestHeader(value = "x-token", required = false) String token, @RequestBody CommanderDto commanderDto){
        HttpStatus status = HttpStatus.OK;
        CommanderDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result =service.create(commanderDto);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }

    @Operation(summary = "Update a Commander by ID (All fields in the same time [integral])")
    @PutMapping("/{id}")
    public ResponseEntity<CommanderDto> update(@RequestHeader(value = "x-token", required = false) String token, @PathVariable(name = "id") String id, @RequestBody CommanderDto commanderDto){
        HttpStatus status = HttpStatus.OK;
        CommanderDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result =service.update(id, commanderDto);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }

    @Operation(summary = "Update a Commander by ID (One or more fields [partial])")
    @PatchMapping("/{id}")
    public ResponseEntity<CommanderDto> patch(@RequestHeader(value = "x-token", required = false) String token, @PathVariable(name = "id") String id, @RequestBody CommanderDto commanderDto){
        HttpStatus status = HttpStatus.OK;
        CommanderDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result =service.patch(id, commanderDto);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }

    @Operation(summary = "Delete a Commander by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommanderDto> delete(@RequestHeader(value = "x-token", required = false) String token, @PathVariable(name = "id") String id){
        HttpStatus status = HttpStatus.OK;
        CommanderDto result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result =service.delete(id);
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }

}
