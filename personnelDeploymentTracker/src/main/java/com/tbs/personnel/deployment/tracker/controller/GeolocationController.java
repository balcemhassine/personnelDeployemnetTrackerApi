package com.tbs.personnel.deployment.tracker.controller;

import com.tbs.personnel.deployment.tracker.dto.GeolocationDto;
import com.tbs.personnel.deployment.tracker.service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geolocation")
public class GeolocationController {
    @Autowired
    GeolocationService service;


    @GetMapping("/")
    public ResponseEntity<List<GeolocationDto>> getAll(){
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeolocationDto> getById(@PathVariable String id){
        GeolocationDto result = service.getById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<GeolocationDto> update(@PathVariable String id, @RequestBody GeolocationDto geolocationDto){
        GeolocationDto result = service.update(id, geolocationDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GeolocationDto> patch(@PathVariable String id, @RequestBody GeolocationDto geolocationDto){
        GeolocationDto result = service.patch(id, geolocationDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeolocationDto> delete(@PathVariable String id){
        GeolocationDto result = service.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
