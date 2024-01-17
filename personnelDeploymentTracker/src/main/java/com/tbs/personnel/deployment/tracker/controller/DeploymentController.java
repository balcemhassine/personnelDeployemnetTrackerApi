package com.tbs.personnel.deployment.tracker.controller;

import com.tbs.personnel.deployment.tracker.dto.DeploymentDto;
import com.tbs.personnel.deployment.tracker.dto.EnlistedDto;
import com.tbs.personnel.deployment.tracker.service.AuthenticationService;
import com.tbs.personnel.deployment.tracker.service.DeploymentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deployment")
public class DeploymentController {

    @Autowired
    DeploymentService deploymentService;
    @Autowired
    AuthenticationService auth;

    @Operation(summary = "Get List of All Deployments")
    @GetMapping("/")
    public ResponseEntity<List<DeploymentDto>> getAll(@RequestHeader(value = "x-token", required = false) String token){
        HttpStatus status = HttpStatus.OK;
        List<DeploymentDto> result  = null;
        try{
            auth.authorization(auth.tokenEvaluation(token), "admin");
            result = deploymentService.getAll();
        } catch (AuthException e) {
            if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                status  = HttpStatus.UNAUTHORIZED;
            } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                status  = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity(result, status);
    }


    @Operation(summary = "Get a Deployment by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DeploymentDto> getById (@RequestHeader(value = "x-token", required = false) String token,@PathVariable(name = "id") String id){
            HttpStatus status = HttpStatus.OK;
            DeploymentDto result  = null;
            try{
                auth.authorization(auth.tokenEvaluation(token), "admin");
                result =deploymentService.getById(id);
            } catch (AuthException e) {
                if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                    status  = HttpStatus.UNAUTHORIZED;
                } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                    status  = HttpStatus.FORBIDDEN;
                }
            }
            return new ResponseEntity(result, status);
    }

    @Operation(summary = "Create a new Deployment")
    @PostMapping("/")
    public ResponseEntity<DeploymentDto> create (@RequestHeader(value = "x-token", required = false) String token,@RequestBody DeploymentDto deploymentDto){
            HttpStatus status = HttpStatus.OK;
            DeploymentDto result  = null;
            try{
                auth.authorization(auth.tokenEvaluation(token), "admin");
                result =deploymentService.create(deploymentDto);
            } catch (AuthException e) {
                if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                    status  = HttpStatus.UNAUTHORIZED;
                } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                    status  = HttpStatus.FORBIDDEN;
                }
            }
            return new ResponseEntity(result, status);}

    @Operation(summary = "Update a Deployment by ID (All fields in the same time [integral])")
    @PutMapping("/{id}")
    public ResponseEntity<DeploymentDto> update (@RequestHeader(value = "x-token", required = false) String token, @PathVariable(name = "id") String id ,@RequestBody DeploymentDto deploymentDto){
            HttpStatus status = HttpStatus.OK;
            DeploymentDto result  = null;
            try{
                auth.authorization(auth.tokenEvaluation(token), "admin");
                result = deploymentService.update(id, deploymentDto);
            } catch (AuthException e) {
                if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                    status  = HttpStatus.UNAUTHORIZED;
                } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                    status  = HttpStatus.FORBIDDEN;
                }
            }
            return new ResponseEntity(result, status);
    }

    @Operation(summary = "Update a Deployment by ID (One or more fields [partial])")
    @PatchMapping("/{id}")
    public ResponseEntity<DeploymentDto> patch (@RequestHeader(value = "x-token", required = false) String token, @PathVariable(name = "id") String id ,@RequestBody DeploymentDto deploymentDto){
            HttpStatus status = HttpStatus.OK;
            DeploymentDto result  = null;
            try{
                auth.authorization(auth.tokenEvaluation(token), "admin");
                result =deploymentService.patch(id, deploymentDto);
            } catch (AuthException e) {
                if( e.getMessage().equals("There are no Token") || e.getMessage().equals("Token is not valid/Expired") ) {
                    status  = HttpStatus.UNAUTHORIZED;
                } else if ( e.getMessage().equals("Not Authorized / insufficient role") ) {
                    status  = HttpStatus.FORBIDDEN;
                }
            }
            return new ResponseEntity(result, status);}


    @Operation(summary = "Delete a Deployment by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<DeploymentDto> delete (@RequestHeader(value = "x-token", required = false) String token, @PathVariable(name = "id") String id){
            HttpStatus status = HttpStatus.OK;
            DeploymentDto result  = null;
            try{
                auth.authorization(auth.tokenEvaluation(token), "admin");
                result =deploymentService.delete(id);
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
