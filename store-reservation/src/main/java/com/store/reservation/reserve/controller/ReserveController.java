package com.store.reservation.reserve.controller;

import com.store.reservation.reserve.dto.ReserveDto;
import com.store.reservation.reserve.entity.ReserveEntity;
import com.store.reservation.reserve.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {

    private final ReserveService reserveService;

    /**
     * @param reserveDto 멤버 매장예약
     * @return 파트너 회원가입 결과값 EX) http://localhost:8080/reserve/member
     */
    @PostMapping("/member")
    public ResponseEntity<ReserveEntity> reserveMember(@RequestBody ReserveDto reserveDto) {
        ReserveEntity reserveEntity = reserveService.reserveService(reserveDto);
        return ResponseEntity.ok(reserveEntity);
    }

    /**
     * @param approvalState 예약 승인,거절
     * @return 예약상태 결과값 http://localhost:8080/{approvalState}
     */
    @GetMapping("/{approvalState}/{reserveId}")
    public ResponseEntity<ReserveEntity> reserveMember(@PathVariable String approvalState, @PathVariable Long reserveId) {
        ReserveEntity reserveEntity = reserveService.approvalState(approvalState, reserveId);
        return ResponseEntity.ok(reserveEntity);
    }
}
