package com.tbs.personnel.deployment.tracker.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "geolocation")
public class Geolocation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String longitude;
    private String latitude;

    public String getId() {
        return this.id;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }
}
