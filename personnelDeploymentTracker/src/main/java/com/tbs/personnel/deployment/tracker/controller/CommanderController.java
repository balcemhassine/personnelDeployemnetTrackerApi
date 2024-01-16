package com.tbs.personnel.deployment.tracker.controller;

import com.tbs.personnel.deployment.tracker.dto.CommanderDto;
import com.tbs.personnel.deployment.tracker.service.CommanderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/commander")
public class CommanderController {

    @Autowired
    CommanderService service;


    @GetMapping("/")
    public ResponseEntity<List<CommanderDto>> getAll(){
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommanderDto> getById(@PathVariable(name = "id") String id){
        CommanderDto result = service.getById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CommanderDto> create(@RequestBody CommanderDto commanderDto){
        CommanderDto result = service.create(commanderDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommanderDto> update(@PathVariable(name = "id") String id, @RequestBody CommanderDto commanderDto){
        CommanderDto result = service.update(id, commanderDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommanderDto> patch(@PathVariable(name = "id") String id, @RequestBody CommanderDto commanderDto){
        CommanderDto result = service.patch(id, commanderDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommanderDto> delete(@PathVariable(name = "id") String id){
        CommanderDto result = service.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
