package com.tbs.personnel.deployment.tracker.mapper;

import com.tbs.personnel.deployment.tracker.dto.SkillDto;
import com.tbs.personnel.deployment.tracker.model.entities.Skill;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillDto toDto(Skill t);
    Skill toEntity(SkillDto b);
    List<Skill> toEntity(List<SkillDto> e);
    List<SkillDto> toDto (List<Skill> e);
}
