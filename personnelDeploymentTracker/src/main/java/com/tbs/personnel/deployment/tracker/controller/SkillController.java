package com.tbs.personnel.deployment.tracker.controller;

import com.tbs.personnel.deployment.tracker.dto.SkillDto;
import com.tbs.personnel.deployment.tracker.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillService service;

    @GetMapping("/")
    public ResponseEntity<List<SkillDto>> getAll(){
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getById(@PathVariable String id){
        SkillDto result = service.getById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SkillDto> create(@RequestBody SkillDto skillDto){
        SkillDto result = service.create(skillDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SkillDto> update(@PathVariable String id, @RequestBody SkillDto skillDto){
        SkillDto result = service.update(id, skillDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SkillDto> patch(@PathVariable String id, @RequestBody SkillDto skillDto){
        SkillDto result = service.patch(id, skillDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SkillDto> delete(@PathVariable String id){
        SkillDto result = service.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
