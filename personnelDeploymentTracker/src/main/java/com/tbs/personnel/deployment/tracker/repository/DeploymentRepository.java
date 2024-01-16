package com.tbs.personnel.deployment.tracker.repository;

import com.tbs.personnel.deployment.tracker.model.entities.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeploymentRepository extends JpaRepository<Deployment, String> {
    Deployment findDeploymentById(String id);
}
