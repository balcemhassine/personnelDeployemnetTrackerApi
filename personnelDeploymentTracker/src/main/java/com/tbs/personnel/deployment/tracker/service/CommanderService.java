package com.tbs.personnel.deployment.tracker.service;

import com.tbs.personnel.deployment.tracker.dto.CommanderDto;
import com.tbs.personnel.deployment.tracker.mapper.CommanderMapper;
import com.tbs.personnel.deployment.tracker.model.entities.Commander;
import com.tbs.personnel.deployment.tracker.repository.CommanderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommanderService {

    @Autowired
    CommanderRepository repository;

    @Autowired
    CommanderMapper mapper;


    public CommanderDto getById(String id){
        Optional<Commander> result = repository.findById(id);
        return mapper.toDto(result.orElseThrow(() -> new EntityNotFoundException()));
    }

    public List<CommanderDto> getAll(){
        List<Commander> resultList = repository.findAll();
        return mapper.toDto(resultList);
    }

    public CommanderDto create(CommanderDto commanderDto){
        Commander toBeSaved = mapper.toEntity(commanderDto);
        Commander savedEntity = repository.save(toBeSaved);
        return mapper.toDto(savedEntity);
    }


    public CommanderDto update(String id, CommanderDto commanderDto){
        Commander toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        toBeUpdated.setPersonnelName(commanderDto.getPersonnelName());
        toBeUpdated.setCommanderRole(commanderDto.getCommanderRole());
        Commander updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);
    }

    public CommanderDto patch(String id, CommanderDto commanderDto){
        Commander toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if(commanderDto.getPersonnelName() != null) toBeUpdated.setPersonnelName(commanderDto.getPersonnelName());
        if(commanderDto.getCommanderRole() != null) toBeUpdated.setCommanderRole(commanderDto.getCommanderRole());
        Commander updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);
    }

    public CommanderDto delete(String id){
        Commander toBeDeleted = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return mapper.toDto(toBeDeleted);
    }
}
