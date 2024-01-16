package com.tbs.personnel.deployment.tracker.repository;

import com.tbs.personnel.deployment.tracker.model.entities.Commander;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommanderRepository extends JpaRepository<Commander, String> {

  Commander findCommanderByIdAndPassword(String id, String password);

}
