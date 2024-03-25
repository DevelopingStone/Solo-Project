package com.store.reservation.admin.service;

import com.store.reservation.admin.dto.StoreDto;
import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.admin.repository.StoreRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    //    상점등록
    @Transactional
    public StoreEntity storeSignUp(StoreDto storeDto, String phoneNumber) {
        StoreEntity storeEntity = storeDto.toEntity(storeDto, phoneNumber);
        return storeRepository.save(storeEntity);
    }

    //    상점전체검색
    @Transactional
    public List<StoreEntity> allSearch() {
        return storeRepository.findAllByStoreNameIsNotNull();
    }

}