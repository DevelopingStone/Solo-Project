package com.store.reservation.member.service;

import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.admin.repository.StoreRepository;
import com.store.reservation.member.dto.ReviewDto;
import com.store.reservation.member.entity.MemberEntity;
import com.store.reservation.member.entity.ReviewEntity;
import com.store.reservation.member.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;

    public StoreEntity storeSearch(String storeSearch) {
        return storeRepository.findByStoreName(storeSearch);
    }


    public ReviewEntity reviewRegister(ReviewDto reviewDto) {
        ReviewEntity reviewEntity = reviewDto.toEntity(reviewDto);
        return reviewRepository.save(reviewEntity);
    }

    public MemberEntity singIn(String phoneNumber) {

        return null;
    }
}
