package com.tbs.personnel.deployment.tracker.controller;

import com.tbs.personnel.deployment.tracker.dto.DeploymentDto;
import com.tbs.personnel.deployment.tracker.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deployment")
public class DeploymentController {

    @Autowired
    DeploymentService deploymentService;

    @GetMapping("/")
    public ResponseEntity<List<DeploymentDto>> getAll(){return new ResponseEntity(deploymentService.getAll(), HttpStatus.OK);}


    @GetMapping("/{id}")
    public ResponseEntity<DeploymentDto> getById (@PathVariable String id){
        DeploymentDto result = deploymentService.getById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<DeploymentDto> create (@RequestBody DeploymentDto deploymentDto){
        DeploymentDto result = deploymentService.create(deploymentDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeploymentDto> update (@PathVariable String id ,@RequestBody DeploymentDto deploymentDto){
        DeploymentDto result = deploymentService.update(id, deploymentDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeploymentDto> patch (@PathVariable String id ,@RequestBody DeploymentDto deploymentDto){
        DeploymentDto result = deploymentService.patch(id, deploymentDto);
        return new ResponseEntity(result, HttpStatus.OK);}

    @DeleteMapping("/{id}")
    public ResponseEntity<DeploymentDto> delete (@PathVariable String id){
        DeploymentDto result = deploymentService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
