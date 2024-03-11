package com.store.reservation.member.controller;

import com.store.reservation.admin.entity.StoreEntity;
import com.store.reservation.auth.controller.SendSMSController;
import com.store.reservation.member.dto.MemberDto;
import com.store.reservation.member.dto.ReviewDto;
import com.store.reservation.member.entity.MemberEntity;
import com.store.reservation.member.entity.ReviewEntity;
import com.store.reservation.member.service.MemberService;
import com.store.reservation.member.service.ReviewService;
import java.util.List;
import java.util.Objects;
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


    private final SendSMSController sendSMSController;
    private final ReviewService reviewService;
    private final MemberService memberService;
    private MemberEntity member;
    private StoreEntity store;
    private String authKey;



    /**
     * @param memberDto 멤버 회원가입
     * @return 회원가입 결과값
     * EX) http://localhost:8080/member/singUp
     */
    @PostMapping("/singUp")
    public ResponseEntity<MemberEntity> singUp(@RequestBody MemberDto memberDto) {
        MemberEntity memberEntity = memberService.singUp(memberDto);
        return ResponseEntity.ok(memberEntity);
    }

    /**
     * @param phoneNumber 핸드폰 인증
     * @return 핸드폰 인증코드
     * EX) http://localhost:8080/member/authKey/01034248212
     */
    @GetMapping("/authKey/{phoneNumber}")
    public ResponseEntity<String> authKey(@PathVariable String phoneNumber) {

        this.member = reviewService.signIn(phoneNumber);
        String message = sendSMSController.sendOne(member.getPhoneNumber());
        this.authKey = message;
        System.out.println("message = " + message);
        return ResponseEntity.ok(message);
    }

    /**
     * @param authKey 인증번호로 로그인
     * @return 로그인 결과값
     * http://localhost:8080/member/singIn/123456
     */
    @GetMapping("/singIn/{authKey}")
    public ResponseEntity<String> singIn(@PathVariable String authKey) {
        if (Objects.equals(this.authKey, authKey)) {
            return ResponseEntity.ok("인증이 완료되었습니다.");
        }
        return ResponseEntity.ok("잘못된 인증키 입니다.");
    }

    /**
     * @param storeSearch 매장검색
     * @return 매장인덱스 결과값
     * EX) http://localhost:8080/member/store/search/당구죠아 3호점
     */
    @GetMapping("/store/search/{storeSearch}")
    public ResponseEntity<StoreEntity> storeSearch(@PathVariable String storeSearch) {
        this.store = reviewService.storeSearch(storeSearch);
        return ResponseEntity.ok(store);
    }

    /**
     * @param reviewDto 리뷰등록 (매장검색, 로그인 없이 리뷰등록할 경우 오류)
     * @return 리뷰 결과값
     * EX) http://localhost:8080/member/review/register
     */
    @PostMapping("/review/register")
    public ResponseEntity<ReviewEntity> reviewRegister(@RequestBody ReviewDto reviewDto) {
        if (this.store == null) {
            throw new IllegalArgumentException("매장검색 먼저 진행해주세요");
        }
        reviewDto.setStore(this.store);
        reviewDto.setMember(this.member);
        ReviewEntity reviewEntity = reviewService.reviewRegister(reviewDto);
        return ResponseEntity.ok(reviewEntity);
    }

    /**
     * 매장리뷰 검색 (매장검색, 로그인 없이 리뷰등록할 경우 오류)
     *
     * @return 매장리뷰 결과값 EX) http://localhost:8080/member/review/search
     */
    @GetMapping("/review/search")
    public ResponseEntity<List<ReviewEntity>> reviewSearch() {
        List<ReviewEntity> reviewEntities = reviewService.reviewSearch(store.getId());
        return ResponseEntity.ok(reviewEntities);
    }


}
