package com.tbs.personnel.deployment.tracker.repository;

import com.tbs.personnel.deployment.tracker.model.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, String> {
    Skill findSkillById(String id);
}
