package com.tbs.personnel.deployment.tracker.controller;

import com.tbs.personnel.deployment.tracker.dto.SkillDto;
import com.tbs.personnel.deployment.tracker.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillService service;

    @Operation(summary = "Get List of All Skills")
    @GetMapping("/")
    public ResponseEntity<List<SkillDto>> getAll(){
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get a Skill by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getById(@PathVariable(name = "id") String id){
        SkillDto result = service.getById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(summary = "Create a new Skill")
    @PostMapping("/")
    public ResponseEntity<SkillDto> create(@RequestBody SkillDto skillDto){
        SkillDto result = service.create(skillDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(summary = "Update a Skill by ID (All fields in the same time [integral])")
    @PutMapping("/{id}")
    public ResponseEntity<SkillDto> update(@PathVariable(name = "id") String id, @RequestBody SkillDto skillDto){
        SkillDto result = service.update(id, skillDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @Operation(summary = "Update a Skill by ID (One or more fields [partial])")
    @PatchMapping("/{id}")
    public ResponseEntity<SkillDto> patch(@PathVariable(name = "id") String id, @RequestBody SkillDto skillDto){
        SkillDto result = service.patch(id, skillDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @Operation(summary = "Delete a Skill by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<SkillDto> delete(@PathVariable(name = "id") String id){
        SkillDto result = service.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
