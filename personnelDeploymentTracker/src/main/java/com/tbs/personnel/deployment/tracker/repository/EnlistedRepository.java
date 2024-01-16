package com.tbs.personnel.deployment.tracker.repository;

import com.tbs.personnel.deployment.tracker.model.entities.Enlisted;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnlistedRepository extends JpaRepository<Enlisted, String> {
    Enlisted findEnlistedByIdAndPassword(String id, String password);
}
