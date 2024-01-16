package com.tbs.personnel.deployment.tracker.dto;

import com.tbs.personnel.deployment.tracker.model.enumerations.LeaveRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto {
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reason;
//    private EnlistedDto enlisted;
    private LeaveRequestStatus Status;
}
