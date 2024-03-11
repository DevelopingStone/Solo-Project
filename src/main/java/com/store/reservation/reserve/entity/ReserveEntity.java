package com.store.reservation.reserve.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.reservation.member.entity.MemberEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.annotation.CreatedDate;

public class ReserveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "approval_state")
    private String approvalState;

    @CreatedDate
    @Column(updatable = false)
    @JoinColumn(name = "reserve_time")
    private LocalDateTime reserveTime ;

    @JoinColumn(name = "arrival_state")
    private String arrivalState;

    @JoinColumn(name = "reserve_state")
    private String reserveState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private MemberEntity member;

}
