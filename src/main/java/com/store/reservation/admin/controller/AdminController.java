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
     * @return 파트너 회원가입 결과값
     */
    @PostMapping("/partners/register")
    public ResponseEntity<PartnerEntity> registerPartner(@RequestBody PartnerDto partnerDto) {
        PartnerEntity partnerEntity = partnerService.partnerSingUp(partnerDto);
        return ResponseEntity.ok(partnerEntity);
    }

//    /**
//     * @param storeDto 파트너 회원가입 여부
//     */
//    @PostMapping("/partners/isSignedUp")
//    public void partnersIsSignedUp(@RequestBody StoreDto storeDto) {
//        partnerService.isSignedUp(storeDto.getPartnerEntity().getPhoneNumber());
//    }

    /**
     * @param storeDto 파트너가입여부조회, 상점등록
     * @return 상점등록 서비스 결과값
     */
    @PostMapping("/stores/create")
    public Object storesCreate(@RequestBody StoreDto storeDto) {
        boolean signedUp = partnerService.isSignedUp(storeDto.getPartnerEntity().getPhoneNumber());
        if (signedUp) {
            StoreEntity storeEntity = storeService.storeSignUp(storeDto);
            return ResponseEntity.ok(storeEntity);
        }
        return "존재하지 않는 회원입니다. 파티너 회원가입 먼저 진행해주세요."; // 또는 다른 적절한 응답을 반환
    }



}
