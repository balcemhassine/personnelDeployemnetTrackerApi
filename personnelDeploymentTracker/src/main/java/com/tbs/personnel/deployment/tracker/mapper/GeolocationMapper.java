package com.tbs.personnel.deployment.tracker.mapper;

import com.tbs.personnel.deployment.tracker.dto.GeolocationDto;
import com.tbs.personnel.deployment.tracker.model.entities.Geolocation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeolocationMapper {
    GeolocationDto toDto(Geolocation t);
    Geolocation toEntity(GeolocationDto b);
    List<Geolocation> toEntity(List<GeolocationDto> e);
    List<GeolocationDto> toDto (List<Geolocation> e);
}
