package com.tbs.personnel.deployment.tracker.mapper;

import com.tbs.personnel.deployment.tracker.dto.EnlistedDto;
import com.tbs.personnel.deployment.tracker.dto.LeaveRequestDto;
import com.tbs.personnel.deployment.tracker.model.entities.Enlisted;
import com.tbs.personnel.deployment.tracker.model.entities.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.List;
@Mapper(componentModel = "spring")
public interface LeaveRequestMapper {
    @Mapping(target = "enlisted", ignore = true)
    LeaveRequestDto toDto(LeaveRequest t);
    @Mapping(target = "enlisted",qualifiedByName = "enlistedDtoMapper", source = "enlisted")
    LeaveRequest toEntity(LeaveRequestDto b);
    List<LeaveRequest> toEntity(List<LeaveRequestDto> e);
    List<LeaveRequestDto> toDto (List<LeaveRequest> e);

    @Named("enlistedDtoMapper")
    public static Enlisted enlistedDtoMapper (EnlistedDto enlistedDto){
        Enlisted enlisted = new Enlisted();
        enlisted.setId(enlistedDto.getId());
        return enlisted;
    }
}
