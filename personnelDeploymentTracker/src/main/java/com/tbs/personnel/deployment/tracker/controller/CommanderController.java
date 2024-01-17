package com.tbs.personnel.deployment.tracker.controller;

import com.tbs.personnel.deployment.tracker.dto.CommanderDto;
import com.tbs.personnel.deployment.tracker.service.CommanderService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get List of All Commanders")
    @GetMapping("/")
    public ResponseEntity<List<CommanderDto>> getAll(){
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get a Commander by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CommanderDto> getById(@PathVariable(name = "id") String id){
        CommanderDto result = service.getById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @Operation(summary = "Create a new Commander")
    @PostMapping("/")
    public ResponseEntity<CommanderDto> create(@RequestBody CommanderDto commanderDto){
        CommanderDto result = service.create(commanderDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(summary = "Update a Commander by ID (All fields in the same time [integral])")
    @PutMapping("/{id}")
    public ResponseEntity<CommanderDto> update(@PathVariable(name = "id") String id, @RequestBody CommanderDto commanderDto){
        CommanderDto result = service.update(id, commanderDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(summary = "Update a Commander by ID (One or more fields [partial])")
    @PatchMapping("/{id}")
    public ResponseEntity<CommanderDto> patch(@PathVariable(name = "id") String id, @RequestBody CommanderDto commanderDto){
        CommanderDto result = service.patch(id, commanderDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Commander by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommanderDto> delete(@PathVariable(name = "id") String id){
        CommanderDto result = service.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
