package com.tbs.personnel.deployment.tracker.service;

import com.tbs.personnel.deployment.tracker.dto.GeolocationDto;
import com.tbs.personnel.deployment.tracker.mapper.GeolocationMapper;
import com.tbs.personnel.deployment.tracker.model.entities.Geolocation;
import com.tbs.personnel.deployment.tracker.repository.GeolocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GeolocationService {
    @Autowired
    GeolocationRepository repository;

    @Autowired
    GeolocationMapper mapper;


    public GeolocationDto getById(String id){
        Optional<Geolocation> result = repository.findById(id);
        return mapper.toDto(result.orElseThrow(() -> new EntityNotFoundException()));
    }

    public List<GeolocationDto> getAll(){
        List<Geolocation> resultList = repository.findAll();
        return mapper.toDto(resultList);
    }

    public GeolocationDto create(GeolocationDto geolocationDto){
        Geolocation toBeSaved = mapper.toEntity(geolocationDto);
        Geolocation savedEntity = repository.save(toBeSaved);
        return mapper.toDto(savedEntity);
    }


    public GeolocationDto update(String id, GeolocationDto geolocationDto){
        Geolocation toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        toBeUpdated.setLongitude(geolocationDto.getLongitude());
        toBeUpdated.setLatitude(geolocationDto.getLatitude());
        Geolocation updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);
    }

    public GeolocationDto patch(String id, GeolocationDto geolocationDto){
        Geolocation toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if(geolocationDto.getLatitude() != null) toBeUpdated.setLatitude(geolocationDto.getLatitude());
        if(geolocationDto.getLongitude() != null) toBeUpdated.setLongitude(geolocationDto.getLongitude());
        Geolocation updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);
    }

    public GeolocationDto delete(String id){
        Geolocation toBeDeleted = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return mapper.toDto(toBeDeleted);
    }
}

