package com.tbs.personnel.deployment.tracker.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String personnelName;

    private String password;
}

