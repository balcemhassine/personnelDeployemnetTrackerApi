package com.tbs.personnel.deployment.tracker.service;

import com.tbs.personnel.deployment.tracker.dto.LeaveRequestDto;
import com.tbs.personnel.deployment.tracker.mapper.EnlistedMapper;
import com.tbs.personnel.deployment.tracker.mapper.LeaveRequestMapper;
import com.tbs.personnel.deployment.tracker.model.entities.LeaveRequest;
import com.tbs.personnel.deployment.tracker.model.enumerations.LeaveRequestStatus;
import com.tbs.personnel.deployment.tracker.repository.LeaveRequestRepository;
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
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestService {

    @Autowired
    LeaveRequestRepository repository;

    @Autowired
    LeaveRequestMapper mapper;
    @Autowired
    EnlistedMapper enlistedMapper;


    public LeaveRequestDto getById(String id){
        Optional<LeaveRequest> result = repository.findById(id);
        return mapper.toDto(result.orElseThrow(() -> new EntityNotFoundException()));
    }

    public List<LeaveRequestDto> getAll(){
        List<LeaveRequest> resultList = repository.findAll();
        return mapper.toDto(resultList);
    }

    public LeaveRequestDto create(LeaveRequestDto leaveRequestDto){
        LeaveRequest toBeSaved = mapper.toEntity(leaveRequestDto);
        toBeSaved.setStatus(LeaveRequestStatus.PENDING);
        LeaveRequest savedEntity = repository.save(toBeSaved);
        return mapper.toDto(savedEntity);    }


    public LeaveRequestDto update(String id, LeaveRequestDto leaveRequestDto){
        LeaveRequest toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        toBeUpdated.setEndDate(leaveRequestDto.getEndDate());
        toBeUpdated.setStartDate(leaveRequestDto.getStartDate());
//        toBeUpdated.setEnlisted(enlistedMapper.toEntity(leaveRequestDto.getEnlisted()));
        toBeUpdated.setReason(leaveRequestDto.getReason());
        toBeUpdated.setStatus(leaveRequestDto.getStatus());
        LeaveRequest updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);
    }

    public LeaveRequestDto patch(String id, LeaveRequestDto leaveRequestDto){
        LeaveRequest toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (leaveRequestDto.getEndDate() != null ) toBeUpdated.setEndDate(leaveRequestDto.getEndDate());
        if (leaveRequestDto.getStartDate() != null ) toBeUpdated.setStartDate(leaveRequestDto.getStartDate());
//        if (leaveRequestDto.getEnlisted() != null ) toBeUpdated.setEnlisted(enlistedMapper.toEntity(leaveRequestDto.getEnlisted()));
        if (leaveRequestDto.getReason() != null ) toBeUpdated.setReason(leaveRequestDto.getReason());
        if (leaveRequestDto.getStatus() != null ) toBeUpdated.setStatus(leaveRequestDto.getStatus());
        LeaveRequest updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);
    }


    public LeaveRequestDto delete(String id){
        LeaveRequest toBeDeleted = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return mapper.toDto(toBeDeleted);    }
}
