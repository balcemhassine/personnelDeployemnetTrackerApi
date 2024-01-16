package com.tbs.personnel.deployment.tracker.dto;

import com.tbs.personnel.deployment.tracker.model.Personnel;
import com.tbs.personnel.deployment.tracker.model.enumerations.HealthStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnlistedDto extends Personnel {

    private String personnelRole;
    private HealthStatus health;
    private List<SkillDto> skills;
    private List<DeploymentDto> deployments;
    private List<LeaveRequestDto> leaveRequests;
    private boolean isDeployed;
}
