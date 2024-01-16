package com.tbs.personnel.deployment.tracker.dto;


import com.tbs.personnel.deployment.tracker.model.Personnel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommanderDto extends Personnel {

    private String commanderRole;
}
