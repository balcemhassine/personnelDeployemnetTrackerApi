package com.tbs.personnel.deployment.tracker.model.entities;

import com.tbs.personnel.deployment.tracker.model.enumerations.DeploymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deployment")
public class Deployment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "geoLocationId")
    private Geolocation geoLocation;

    @ManyToMany
    Set<Enlisted> enlisted ;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private DeploymentStatus status;

}
