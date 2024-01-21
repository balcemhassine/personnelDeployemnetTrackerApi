package com.tbs.personnel.deployment.tracker.model.entities;

import com.tbs.personnel.deployment.tracker.model.enumerations.LeaveRequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leave_request")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enlisted_id" , nullable = false)
    private Enlisted enlisted;

    @Enumerated(EnumType.STRING)
    private LeaveRequestStatus status;
    private String reasonOfRejection;
}
