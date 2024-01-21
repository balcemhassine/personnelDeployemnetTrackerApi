package com.tbs.personnel.deployment.tracker.repository;

import com.tbs.personnel.deployment.tracker.model.entities.LeaveRequest;
import com.tbs.personnel.deployment.tracker.model.enumerations.LeaveRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, String> {

    Optional<LeaveRequest> findLeaveRequestByIdAndStatus(String id, LeaveRequestStatus leaveRequestStatus);

}
