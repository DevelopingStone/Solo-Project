package com.example.weddinginvitation.member.service;

import com.example.weddinginvitation.member.dto.MemberDto;
import com.example.weddinginvitation.member.entity.MemberEntity;
import com.example.weddinginvitation.member.repository.MemberRepository;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberEntity singUp(MemberDto memberDto, HashMap<String, Object> userInfo) {

        MemberEntity memberEntity = memberDto.toEntity(memberDto, userInfo);
        return memberRepository.save(memberEntity);

    }

}
