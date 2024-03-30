package com.example.weddinginvitation.member.service;

import com.example.weddinginvitation.member.dto.MemberDto;
import com.example.weddinginvitation.member.entity.MemberEntity;
import com.example.weddinginvitation.member.repository.MemberRepository;
import java.util.HashMap;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberDto memberDto;

    public MemberEntity kakaoLogin(HashMap<String, Object> userInfo) {

        MemberEntity kakaoLoginData = memberDto.toEntity(userInfo);
        return memberRepository.save(kakaoLoginData);

    }

    public MemberEntity sendSms(MemberDto memberDto, String authenticationNumber) {

        Optional<MemberEntity> byEmail = memberRepository.findByEmail(memberDto.getEmail());

        if (byEmail.isPresent()) {
            MemberEntity existingMember = byEmail.get();
            MemberEntity updatedMember = memberDto.toEntity(existingMember, authenticationNumber);
            return memberRepository.save(updatedMember);
        } else {
            throw new RuntimeException(memberDto.getEmail() + " : 메일은 카카오 회원가입이 되지 않았습니다.");
        }

    }

    public MemberEntity sendSmsCheck(String authenticationNumber) {
        Optional<MemberEntity> byTextAuthenticationNumber = memberRepository.findByTextAuthenticationNumber(
                authenticationNumber);
        if (byTextAuthenticationNumber.isPresent()) {
            MemberEntity existingMember = byTextAuthenticationNumber.get();
            memberDto.toEntity(existingMember);
            return memberRepository.save(existingMember);
        } else {
            throw new RuntimeException("인증번호가 잘못되었습니다.");
        }
    }

//    public MemberEntity singUp(MemberDto memberDto, HashMap<String, Object> userInfo, String authenticationNumber) {
//        MemberEntity memberEntity = memberDto.toEntity(memberDto, userInfo, authenticationNumber);
//        return memberRepository.save(memberEntity);
//    }

}
