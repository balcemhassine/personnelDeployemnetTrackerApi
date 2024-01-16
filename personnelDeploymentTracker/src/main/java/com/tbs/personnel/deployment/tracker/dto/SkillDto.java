package com.tbs.personnel.deployment.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {
    private String id;
    private String skillName;
    private String skillDescription;
}
