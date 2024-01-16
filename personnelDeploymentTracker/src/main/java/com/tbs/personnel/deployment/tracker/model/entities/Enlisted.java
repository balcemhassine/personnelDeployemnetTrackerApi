package com.tbs.personnel.deployment.tracker.model.entities;


import com.tbs.personnel.deployment.tracker.model.enumerations.HealthStatus;
import com.tbs.personnel.deployment.tracker.model.Personnel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "enlisted")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Enlisted extends Personnel {
    private String personnelRole;

    @Enumerated(EnumType.STRING)
    private HealthStatus health;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Deployment> deployments;

    @OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<LeaveRequest> leaveRequests;

    private boolean isDeployed;

    public Enlisted(String personnelRole, HealthStatus health, List<Skill> skills, List<Deployment> deployments, List<LeaveRequest> leaveRequests, boolean isDeployed) {
        this.personnelRole = personnelRole;
        this.health = health;
        this.skills = skills;
        this.deployments = deployments;
        this.leaveRequests = leaveRequests;
        this.isDeployed = isDeployed;
    }

    public Enlisted() {
    }

    public String getPersonnelRole() {
        return this.personnelRole;
    }

    public HealthStatus getHealth() {
        return this.health;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public List<Deployment> getDeployments() {
        return this.deployments;
    }

    public List<LeaveRequest> getLeaveRequests() {
        return this.leaveRequests;
    }

    public boolean isDeployed() {
        return this.isDeployed;
    }

    public void setPersonnelRole(String personnelRole) {
        this.personnelRole = personnelRole;
    }

    public void setHealth(HealthStatus health) {
        this.health = health;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void setDeployments(List<Deployment> deployments) {
        this.deployments = deployments;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public void setDeployed(boolean isDeployed) {
        this.isDeployed = isDeployed;
    }
}
