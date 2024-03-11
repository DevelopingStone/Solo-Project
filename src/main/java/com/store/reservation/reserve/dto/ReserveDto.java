package com.store.reservation.reserve.dto;

import com.store.reservation.reserve.entity.ReserveEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReserveDto {

    @JoinColumn(name = "approval_state")
    private String approvalState;

    @CreatedDate
    @Column(updatable = false)
    @JoinColumn(name = "reserve_time")
    private LocalDateTime reserveTime;

    @JoinColumn(name = "arrival_state")
    private String arrivalState;
    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    @JsonIgnore
    private Long memberId;

    public ReserveEntity toEntity(ReserveDto reserveDto) {
        return ReserveEntity.builder().
                approvalState(reserveDto.getApprovalState()).
                reserveTime(reserveDto.getReserveTime()).
                arrivalState(reserveDto.getArrivalState()).
                memberId(reserveDto.getMemberId()).
                build();
    }
}
