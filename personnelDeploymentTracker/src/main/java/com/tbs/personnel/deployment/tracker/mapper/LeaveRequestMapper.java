package com.tbs.personnel.deployment.tracker.mapper;

import com.tbs.personnel.deployment.tracker.dto.LeaveRequestDto;
import com.tbs.personnel.deployment.tracker.model.entities.LeaveRequest;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring", uses = EnlistedMapper.class)
public interface LeaveRequestMapper {
    LeaveRequestDto toDto(LeaveRequest t);
    LeaveRequest toEntity(LeaveRequestDto b);
    List<LeaveRequest> toEntity(List<LeaveRequestDto> e);
    List<LeaveRequestDto> toDto (List<LeaveRequest> e);
}
