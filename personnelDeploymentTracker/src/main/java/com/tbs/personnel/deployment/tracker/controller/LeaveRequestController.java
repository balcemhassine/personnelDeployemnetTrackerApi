package com.tbs.personnel.deployment.tracker.controller;


import com.tbs.personnel.deployment.tracker.dto.LeaveRequestDto;
import com.tbs.personnel.deployment.tracker.service.AuthenticationService;
import com.tbs.personnel.deployment.tracker.service.LeaveRequestService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave-request")
public class LeaveRequestController {
    @Autowired
    LeaveRequestService service;

    @Autowired
    AuthenticationService auth;

    @Operation(summary = "Get List of All Leave Requests ")
    @GetMapping("/")
    public ResponseEntity<List<LeaveRequestDto>> getAll(@RequestHeader(value = "x-token", required = false) String token){
        HttpStatus status = HttpStatus.OK;
        List<LeaveRequestDto> result  = null;
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
    @Operation(summary = "Get a Leave Request by ID")
    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> getById(@PathVariable(name = "id") String id){
        LeaveRequestDto result = service.getById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(summary = "Create a new Leave Request")
    @PostMapping("/")
    public ResponseEntity<LeaveRequestDto> create(@RequestBody LeaveRequestDto leaveRequestDto){
        LeaveRequestDto result = service.create(leaveRequestDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(summary = "Update a Leave Request by ID (All fields in the same time [integral])")
    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> update(@PathVariable(name = "id") String id, @RequestBody LeaveRequestDto leaveRequestDto){
        LeaveRequestDto result = service.update(id, leaveRequestDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(summary = "Update a Leave Request by ID (One or more fields [partial])")
    @PatchMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> patch(@PathVariable(name = "id") String id, @RequestBody LeaveRequestDto leaveRequestDto){
        LeaveRequestDto result = service.patch(id, leaveRequestDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Leave Request by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> delete(@PathVariable(name = "id") String id){
        LeaveRequestDto result = service.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
