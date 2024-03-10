package com.store.reservation.admin.controller;

import com.store.reservation.admin.dto.PartnerDto;
import com.store.reservation.admin.dto.StoreDto;
import com.store.reservation.admin.entity.PartnerEntity;
import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.admin.service.PartnerService;
import com.store.reservation.admin.service.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @return 파트너 회원가입 결과값 EX) http://localhost:8080/admin/partners/register
     */
    @PostMapping("/partners/register")
    public ResponseEntity<PartnerEntity> registerPartner(@RequestBody PartnerDto partnerDto) {
        PartnerEntity partnerEntity = partnerService.partnerSingUp(partnerDto);
        return ResponseEntity.ok(partnerEntity);
    }


    /**
     * @param storeDto 파트너회원가입 조회, 점장 상점등록
     * @return 상점등록 서비스 결과값 EX) http://localhost:8080/admin/stores/create
     */
    @PostMapping("/stores/create")
    public Object storesCreate(@RequestBody StoreDto storeDto) {
        String phoneNumber = "";
        boolean signedUp = partnerService.isSignedUp(storeDto.getPhoneNumber());
        if (signedUp) {
            phoneNumber = storeDto.getPhoneNumber();
            StoreEntity storeEntity = storeService.storeSignUp(storeDto, phoneNumber);
            return ResponseEntity.ok(storeEntity);
        }
        return "상점등록 하기 위해서는 파트너 회원가입 먼저 진행해주시길 바랍니다.";
    }

    /**
     * 매장 검색
     * @return 매장인덱스 결과값
     * EX) http://localhost:8080/admin/store/all/search/
     */
    @GetMapping("/store/all/search")
    public ResponseEntity<List<StoreEntity>> AllSearch() {
        List<StoreEntity> allSearch = storeService.allSearch();
        return ResponseEntity.ok(allSearch);
    }


}
