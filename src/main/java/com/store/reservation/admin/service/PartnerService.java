package com.store.reservation.admin.service;

import com.store.reservation.admin.dto.PartnerDto;
import com.store.reservation.admin.entity.PartnerEntity;
import com.store.reservation.admin.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;

    //    회원가입
    public PartnerEntity partnerSingUp(PartnerDto partnerDto) {
        PartnerEntity entity = partnerDto.toEntity(partnerDto);
        return partnerRepository.save(entity);
    }

    //    파트너회원가입 조회
    public boolean isSignedUp(String phoneNumber) {
        PartnerEntity partner = partnerRepository.findByPhoneNumber(phoneNumber)
                .orElse(null);
        return partner != null;
    }
}
