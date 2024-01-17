package com.tbs.personnel.deployment.tracker.controller;


import com.tbs.personnel.deployment.tracker.dto.LeaveRequestDto;
import com.tbs.personnel.deployment.tracker.service.LeaveRequestService;
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

    @GetMapping("/")
    public ResponseEntity<List<LeaveRequestDto>> getAll(){
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> getById(@PathVariable String id){
        LeaveRequestDto result = service.getById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<LeaveRequestDto> create(@RequestBody LeaveRequestDto leaveRequestDto){
        LeaveRequestDto result = service.create(leaveRequestDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> update(@PathVariable String id, @RequestBody LeaveRequestDto leaveRequestDto){
        LeaveRequestDto result = service.update(id, leaveRequestDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> patch(@PathVariable String id, @RequestBody LeaveRequestDto leaveRequestDto){
        LeaveRequestDto result = service.patch(id, leaveRequestDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> delete(@PathVariable String id){
        LeaveRequestDto result = service.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
