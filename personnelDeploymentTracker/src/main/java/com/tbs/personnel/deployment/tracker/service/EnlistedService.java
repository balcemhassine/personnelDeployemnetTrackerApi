package com.tbs.personnel.deployment.tracker.service;


import com.tbs.personnel.deployment.tracker.dto.EnlistedDto;
import com.tbs.personnel.deployment.tracker.dto.EnrollmentDto;
import com.tbs.personnel.deployment.tracker.mapper.DeploymentMapper;
import com.tbs.personnel.deployment.tracker.mapper.EnlistedMapper;
import com.tbs.personnel.deployment.tracker.mapper.LeaveRequestMapper;
import com.tbs.personnel.deployment.tracker.mapper.SkillMapper;
import com.tbs.personnel.deployment.tracker.model.entities.Deployment;
import com.tbs.personnel.deployment.tracker.model.entities.Enlisted;
import com.tbs.personnel.deployment.tracker.model.entities.LeaveRequest;
import com.tbs.personnel.deployment.tracker.model.enumerations.LeaveRequestStatus;
import com.tbs.personnel.deployment.tracker.repository.DeploymentRepository;
import com.tbs.personnel.deployment.tracker.repository.EnlistedRepository;
import com.tbs.personnel.deployment.tracker.repository.LeaveRequestRepository;
import com.tbs.personnel.deployment.tracker.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class EnlistedService {

    @Autowired
    EnlistedRepository repository;

    @Autowired
    DeploymentRepository deploymentRepository;

    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    EnlistedMapper mapper;

    @Autowired
    DeploymentMapper deploymentMapper;

    @Autowired
    LeaveRequestMapper leaveRequestMapper;

    @Autowired
    SkillMapper skillMapper;


    public EnlistedDto getById(String id){
        Optional<Enlisted> result = repository.findById(id);
        return mapper.toDto(result.orElseThrow(() -> new EntityNotFoundException()));    }

    public List<EnlistedDto> getAll(){
        List<Enlisted> resultList = repository.findAll();
        return mapper.toDto(resultList);
    }

    public EnlistedDto create(EnlistedDto enlistedDto){
        Enlisted toBeCreated = mapper.toEntity(enlistedDto);
        toBeCreated.setDeployments(toBeCreated.getDeployments()
                .stream()
                .map(
                        deployment -> deploymentRepository.findById(deployment.getId()).orElseThrow(() -> new EntityNotFoundException())
                ).collect(Collectors.toList()));

        toBeCreated.setSkills(toBeCreated.getSkills()
                .stream()
                .map(
                        skill -> skillRepository.findById(skill.getId()).orElseThrow(() -> new EntityNotFoundException())
                ).collect(Collectors.toList()));
        toBeCreated.setLeaveRequests(toBeCreated.getLeaveRequests()
                .stream()
                .map(
                        leaveRequest -> leaveRequestRepository.findById(leaveRequest.getId()).orElseThrow(() -> new EntityNotFoundException())
                ).collect(Collectors.toList()) );
        Enlisted savedEntity = repository.save(toBeCreated);
        return mapper.toDto(savedEntity);
    }


    public EnlistedDto update(String id, EnlistedDto enlistedDto){
        Enlisted toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        toBeUpdated.setPersonnelName(enlistedDto.getPersonnelName());
        toBeUpdated.setPersonnelRole(enlistedDto.getPersonnelRole());
        toBeUpdated.setHealth(enlistedDto.getHealth());
        toBeUpdated.setSkills(skillMapper.toEntity(enlistedDto.getSkills()));
        toBeUpdated.setDeployments(deploymentMapper.ToEntity(enlistedDto.getDeployments()));
        toBeUpdated.setLeaveRequests(leaveRequestMapper.toEntity(enlistedDto.getLeaveRequests()));
        toBeUpdated.setDeployed(enlistedDto.isDeployed());      //to recheck
        Enlisted updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);
    }

    public EnlistedDto patch (String id , EnlistedDto enlistedDto){
        Enlisted toBeUpdated = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (enlistedDto.getPersonnelName() != null ) toBeUpdated.setPersonnelName(enlistedDto.getPersonnelName());
        if (enlistedDto.getPersonnelRole()!= null ) toBeUpdated.setPersonnelRole(enlistedDto.getPersonnelRole());
        if (enlistedDto.getHealth()!= null ) toBeUpdated.setHealth(enlistedDto.getHealth());
        if (enlistedDto.getSkills()!= null ) toBeUpdated.setSkills(skillMapper.toEntity(enlistedDto.getSkills()));
        if (enlistedDto.getDeployments()!= null ) toBeUpdated.setDeployments(deploymentMapper.ToEntity(enlistedDto.getDeployments()));
        if (enlistedDto.getLeaveRequests() != null ) toBeUpdated.setLeaveRequests(leaveRequestMapper.toEntity(enlistedDto.getLeaveRequests()));
        // toBeUpdated.setDeployed(enlistedDto.isDeployed());      //to recheck
        Enlisted updatedEntity = repository.save(toBeUpdated);
        return mapper.toDto(updatedEntity);

    }

    public EnlistedDto delete(String id){
        Enlisted toBeDeleted = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return mapper.toDto(toBeDeleted);
    }

    public Boolean enrollEnlisted(EnrollmentDto enrollment){
        // retrieve concerned enlisted and deployment
          Enlisted concernedEnlisted = repository.findById(enrollment.getEnlistedId()).orElseThrow(() -> new EntityNotFoundException());
          Deployment concernedDeployment = deploymentRepository.findById(enrollment.getDeploymentId()).orElseThrow(() -> new EntityNotFoundException());

        LocalDateTime intendedStartDate = concernedDeployment.getStartDate();
        LocalDateTime intendedEndDate = concernedDeployment.getEndDate();


        // check request leave conflict
        List<LeaveRequest> leaveRequest = concernedEnlisted
                .getLeaveRequests()
                .stream()
                .filter(lr -> lr.getStatus().equals(LeaveRequestStatus.ACCEPTED))
                .collect(Collectors.toList());

        List<LeaveRequest> conflictLeaveRequests = leaveRequest.stream().filter(lr ->
                 (lr.getStartDate().isAfter(intendedStartDate) && lr.getStartDate().isBefore(intendedEndDate))
                || (lr.getEndDate().isAfter(intendedStartDate) && lr.getEndDate().isBefore(intendedEndDate))

                ).collect(Collectors.toList());


        // check scheduled deployment conflict
        List<Deployment> deployments = concernedEnlisted.getDeployments();
        List<Deployment> conflictDeployments = deployments.stream().filter(dep ->
                (dep.getStartDate().isAfter(intendedStartDate) && dep.getStartDate().isBefore(intendedEndDate))
                        || (dep.getEndDate().isAfter(intendedStartDate) && dep.getEndDate().isBefore(intendedEndDate))).collect(Collectors.toList());

        //result processing
        boolean isOk = conflictDeployments.isEmpty() && conflictLeaveRequests.isEmpty();

        //if ok enroll concerned enlisted to the concerned deployment
        if(isOk) {
            List<Deployment> enlistedInDeployment = concernedEnlisted.getDeployments();
            enlistedInDeployment.add(concernedDeployment);
            concernedEnlisted.setDeployments(enlistedInDeployment);
            repository.save(concernedEnlisted);
        }

        //else return false (enrollment failed)

        return isOk;
    }


}
