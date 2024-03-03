package com.store.reservation.admin.service;

import com.store.reservation.admin.dto.StoreDto;
import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.admin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreEntity singUp(StoreDto storeDto){
        StoreEntity entity = storeDto.toEntity(storeDto);
        return storeRepository.save(entity);

    }


}