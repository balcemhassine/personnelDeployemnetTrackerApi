package com.tbs.personnel.deployment.tracker.repository;

import com.tbs.personnel.deployment.tracker.model.entities.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeolocationRepository extends JpaRepository<Geolocation, String> {
}
