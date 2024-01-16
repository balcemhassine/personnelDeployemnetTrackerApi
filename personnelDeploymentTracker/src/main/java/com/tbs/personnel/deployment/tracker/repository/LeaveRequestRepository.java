package com.tbs.personnel.deployment.tracker.repository;

import com.tbs.personnel.deployment.tracker.model.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, String> {
    LeaveRequest findLeaveRequestById(String id);


}
