package com.store.reservation.reserve.service;


import com.store.reservation.reserve.dto.ReserveDto;
import com.store.reservation.reserve.entity.ReserveEntity;
import com.store.reservation.reserve.repository.ReserveRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final ReserveRepository reserveRepository;

    //    멤버 매장예약
    public ReserveEntity reserveService(ReserveDto reserveDto) {
        ReserveEntity entity = reserveDto.toEntity(reserveDto);
        return reserveRepository.save(entity);
    }

    //   점장 매장승인,거절
    public ReserveEntity approvalState(String approvalState, Long reserveId) {
        Optional<ReserveEntity> optionalReserve = reserveRepository.findById(reserveId);
        if (optionalReserve.isPresent()) {
            ReserveEntity reserveEntity = optionalReserve.get();
            reserveEntity.setApprovalState(approvalState);
            return reserveRepository.save(reserveEntity);
        } else {
            throw new IllegalArgumentException("해당 ID에 대한 예약이 없습니다.");
        }
    }
}