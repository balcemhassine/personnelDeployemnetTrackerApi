package com.tbs.personnel.deployment.tracker.mapper;

import com.tbs.personnel.deployment.tracker.dto.EnlistedDto;
import com.tbs.personnel.deployment.tracker.model.entities.Enlisted;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DeploymentMapper.class, LeaveRequestMapper.class ,SkillMapper.class})
public interface EnlistedMapper {

    EnlistedDto toDto(Enlisted t);
    Enlisted toEntity(EnlistedDto b);
    List<Enlisted> toEntity(List<EnlistedDto> e);
    List<EnlistedDto> toDto (List<Enlisted> e);
}
