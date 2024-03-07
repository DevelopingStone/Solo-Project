package com.store.reservation.member.controller;

import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.admin.repository.StoreRepository;
import com.store.reservation.member.dto.ReviewDto;
import com.store.reservation.member.entity.ReviewEntity;
import com.store.reservation.member.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {


    private final ReviewService reviewService;

    private final StoreRepository storeRepository;

    private StoreEntity store;

    /**
     * @param storeSearch 매장검색
     * @return 매장인덱스 결과값
     */
    @GetMapping("/store/search/{storeSearch}")
    public ResponseEntity<StoreEntity> storeSearch(@PathVariable String storeSearch) {
        this.store = reviewService.storeSearch(storeSearch);
        return ResponseEntity.ok(store);
    }

    /**
     * @param reviewDto 리뷰등록
     * @return 리뷰 결과값
     */
    @PostMapping("/review/register")
    public ResponseEntity<ReviewEntity> reviewRegister(@RequestBody ReviewDto reviewDto) {
        if (this.store == null) {
            throw new IllegalArgumentException("검색을 먼저 진행해주세요");
        }
        reviewDto.setStore(this.store);
        ReviewEntity reviewEntity = reviewService.reviewRegister(reviewDto);
        return ResponseEntity.ok(reviewEntity);
    }


}
