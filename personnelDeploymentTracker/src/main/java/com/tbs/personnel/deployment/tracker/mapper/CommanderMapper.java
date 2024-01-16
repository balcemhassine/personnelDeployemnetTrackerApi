package com.tbs.personnel.deployment.tracker.mapper;

import com.tbs.personnel.deployment.tracker.dto.CommanderDto;
import com.tbs.personnel.deployment.tracker.model.entities.Commander;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CommanderMapper {
    CommanderDto toDto(Commander t);
    Commander toEntity(CommanderDto b);
    List<Commander> toEntity(List<CommanderDto> e);
    List<CommanderDto> toDto (List<Commander> e);
}
