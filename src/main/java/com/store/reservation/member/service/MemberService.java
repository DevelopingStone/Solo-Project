package com.store.reservation.member.service;

import com.store.reservation.member.dto.MemberDto;
import com.store.reservation.member.entity.MemberEntity;
import com.store.reservation.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MemberService {

    private final MemberRepository memberRepository;

//    멤버회원 회원가입
    public MemberEntity singUp(MemberDto memberDto) {
        MemberEntity memberEntity = memberDto.toEntity(memberDto);
        return memberRepository.save(memberEntity);

    }
}
