package com.store.reservation.admin.service;

import com.store.reservation.admin.dto.StoreDto;
import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.admin.repository.PartnerRepository;
import com.store.reservation.admin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final PartnerRepository partnerRepository;

//    public StoreEntity singUp(StoreDto storeDto){
//        StoreEntity entity = storeDto.toEntity(storeDto);
//        return storeRepository.save(entity);
//
//    }

    //    상점등록
    @Transactional
    public StoreEntity storeSignUp(StoreDto storeDto, String phoneNumber) {
        StoreEntity storeEntity = storeDto.toEntity(storeDto,phoneNumber);
        return storeRepository.save(storeEntity);
    }


}