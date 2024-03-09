package com.store.reservation.member.service;

import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.admin.repository.StoreRepository;
import com.store.reservation.member.dto.ReviewDto;
import com.store.reservation.member.entity.MemberEntity;
import com.store.reservation.member.entity.ReviewEntity;
import com.store.reservation.member.repository.MemberRepository;
import com.store.reservation.member.repository.ReviewRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;

    private final MemberRepository memberRepository;

    public StoreEntity storeSearch(String storeSearch) {
        return storeRepository.findByStoreName(storeSearch);
    }

//   멤버회원 리뷰작성
    public ReviewEntity reviewRegister(ReviewDto reviewDto) {
        ReviewEntity reviewEntity = reviewDto.toEntity(reviewDto);
        return reviewRepository.save(reviewEntity);
    }

//    멤버회원 로그인
    public MemberEntity signIn(String phoneNumber) {
        Optional<MemberEntity> memberOptional = memberRepository.findByPhoneNumber(phoneNumber);
        return memberOptional.orElseThrow(() -> new IllegalArgumentException("핸드폰 번호로 가입된 회원이 없습니다."));
    }
}
