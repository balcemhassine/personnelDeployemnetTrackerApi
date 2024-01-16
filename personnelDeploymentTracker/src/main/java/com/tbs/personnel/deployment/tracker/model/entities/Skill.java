package com.tbs.personnel.deployment.tracker.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String skillName;
    private String skillDescription;
    @ManyToOne
    @JoinColumn(name="personnel_id", insertable = false , updatable = false)
    private Enlisted enlisted;
}
