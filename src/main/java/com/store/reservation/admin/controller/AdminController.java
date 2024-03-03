package com.store.reservation.admin.controller;

import com.store.reservation.admin.dto.PartnerDto;
import com.store.reservation.admin.dto.StoreDto;
import com.store.reservation.admin.entity.PartnerEntity;
import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.admin.service.PartnerService;
import com.store.reservation.admin.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PartnerService partnerService;

    private final StoreService storeService;

    /**
     * @param partnerDto 파트너회원가입
     * @return 파트너회원가입 결과값
     */
    @PostMapping("/partners/register")
    public ResponseEntity<PartnerEntity> registerPartner(@RequestBody PartnerDto partnerDto) {
        PartnerEntity partnerEntity = partnerService.singUp(partnerDto);
        return ResponseEntity.ok(partnerEntity);
    }


    /**
     * @param storeDto 상점등록
     * @return 상점등록서비스 결과값
     */
    @PostMapping("/stores/create")
    public ResponseEntity<StoreEntity> storesCreate(@RequestBody StoreDto storeDto) {
        StoreEntity storeEntity = storeService.singUp(storeDto);
        return ResponseEntity.ok(storeEntity);
    }


}
