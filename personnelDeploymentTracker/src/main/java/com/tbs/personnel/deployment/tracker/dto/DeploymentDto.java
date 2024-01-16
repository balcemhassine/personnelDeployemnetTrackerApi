package com.tbs.personnel.deployment.tracker.dto;

import com.tbs.personnel.deployment.tracker.model.enumerations.DeploymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeploymentDto {
    private String id;
    private GeolocationDto geoLocation;
    private DeploymentStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
