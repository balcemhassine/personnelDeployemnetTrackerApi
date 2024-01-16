package com.tbs.personnel.deployment.tracker.service;


import com.tbs.personnel.deployment.tracker.dto.DeploymentDto;
import com.tbs.personnel.deployment.tracker.mapper.DeploymentMapper;
import com.tbs.personnel.deployment.tracker.mapper.GeolocationMapper;
import com.tbs.personnel.deployment.tracker.model.entities.Deployment;
import com.tbs.personnel.deployment.tracker.model.entities.Geolocation;
import com.tbs.personnel.deployment.tracker.repository.DeploymentRepository;
import com.tbs.personnel.deployment.tracker.repository.GeolocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class DeploymentService {

    @Autowired
    DeploymentRepository repository;

    @Autowired
    GeolocationRepository geolocationRepository;

    @Autowired
    DeploymentMapper deploymentMapper;

    @Autowired
    GeolocationMapper geolocationMapper;


    public DeploymentDto getById(String id){
        Optional<Deployment> result = repository.findById(id);
        return deploymentMapper.toDto(result.orElseThrow(() -> new EntityNotFoundException()));
    }

    public List<DeploymentDto> getAll(){
        List<Deployment> resultList = repository.findAll();
        return deploymentMapper.toDto(resultList);
    }

    public DeploymentDto create(DeploymentDto deploymentDto){
        Deployment toBeSaved = deploymentMapper.toEntity(deploymentDto);
        if(toBeSaved.getGeoLocation().getId() != null) {
            Geolocation geolocation = geolocationRepository.findById(toBeSaved.getGeoLocation().getId()).orElseThrow(() -> new EntityNotFoundException());
            toBeSaved.setGeoLocation(geolocation);
        }
        Deployment savedEntity= repository.save(toBeSaved);
        return deploymentMapper.toDto(savedEntity);
    }


    public DeploymentDto update(String id, DeploymentDto deploymentDto){
        Deployment toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        toBeUpdated.setGeoLocation(geolocationMapper.toEntity(deploymentDto.getGeoLocation()));
        toBeUpdated.setStatus(deploymentDto.getStatus());
        Deployment updatedEntity = repository.save(toBeUpdated);
        return deploymentMapper.toDto(updatedEntity);
    }

    public DeploymentDto patch (String id , DeploymentDto deploymentDto){
        Deployment toBeUpdated = repository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        if(deploymentDto.getGeoLocation() != null) toBeUpdated.setGeoLocation(geolocationMapper.toEntity(deploymentDto.getGeoLocation()));
        if (deploymentDto.getStatus()!= null) toBeUpdated.setStatus(deploymentDto.getStatus());
        Deployment updatedEntity = repository.save(toBeUpdated);
        return deploymentMapper.toDto(updatedEntity);
    }

    public DeploymentDto delete(String id){
        Deployment toBeDeleted = repository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        repository.deleteById(id);
        return deploymentMapper.toDto(toBeDeleted);
    }
}
