package com.tbs.personnel.deployment.tracker.model.entities;


import com.tbs.personnel.deployment.tracker.model.Personnel;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "commander")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Commander extends Personnel {
    private String commanderRole;
}
