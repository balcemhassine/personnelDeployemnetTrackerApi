package com.tbs.personnel.deployment.tracker.mapper;

import com.tbs.personnel.deployment.tracker.dto.DeploymentDto;
import com.tbs.personnel.deployment.tracker.model.entities.Deployment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = GeolocationMapper.class)
public interface DeploymentMapper {
    DeploymentDto toDto(Deployment d);
    Deployment toEntity(DeploymentDto b);
    List<Deployment> ToEntity(List<DeploymentDto> e);
    List<DeploymentDto> toDto (List<Deployment> e);

}
