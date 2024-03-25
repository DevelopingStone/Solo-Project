package com.store.reservation.reserve.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class ReserveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "approval_state")
    private String approvalState;

    @CreatedDate
    @Column(updatable = false)
    @JoinColumn(name = "reserve_time")
    private LocalDateTime reserveTime;

    @JoinColumn(name = "arrival_state")
    private String arrivalState;

    @JoinColumn(name = "member_id")
    private Long memberId;

}
