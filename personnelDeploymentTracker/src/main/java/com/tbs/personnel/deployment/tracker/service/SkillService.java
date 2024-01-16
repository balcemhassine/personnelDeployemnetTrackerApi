package com.tbs.personnel.deployment.tracker.service;

import com.tbs.personnel.deployment.tracker.dto.SkillDto;
import com.tbs.personnel.deployment.tracker.mapper.SkillMapper;
import com.tbs.personnel.deployment.tracker.model.entities.Skill;
import com.tbs.personnel.deployment.tracker.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillService {

    @Autowired
    SkillRepository repository;

    @Autowired
    SkillMapper mapper;

    public SkillDto getById(String id){
        Optional<Skill> result = repository.findById(id);
        return mapper.toDto(result.orElseThrow(() -> new EntityNotFoundException()));
    }

    public List<SkillDto> getAll(){
        List<Skill> resultList = repository.findAll();
        return mapper.toDto(resultList);
    }

    public SkillDto create(SkillDto skillDto){
        Skill toBeSaved = mapper.toEntity(skillDto);
        Skill savedEntity = repository.save(toBeSaved);
        return mapper.toDto(savedEntity);
    }


    public SkillDto update(String id, SkillDto skillDto){
        Skill toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        toBeUpdated.setSkillName(skillDto.getSkillName());
        toBeUpdated.setSkillDescription(skillDto.getSkillDescription());
        Skill updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);
    }

    public SkillDto patch(String id, SkillDto skillDto){
        Skill toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (skillDto.getSkillName() != null ) toBeUpdated.setSkillName(skillDto.getSkillName());
        if (skillDto.getSkillDescription() != null )toBeUpdated.setSkillDescription(skillDto.getSkillDescription());
        Skill updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);
    }

    public SkillDto delete(String id){
        Skill toBeDeleted = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return mapper.toDto(toBeDeleted);
    }
}
