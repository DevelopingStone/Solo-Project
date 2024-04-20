package com.example.weddinginvitation.security.config;

import com.example.weddinginvitation.member.entity.MemberEntity;
import com.example.weddinginvitation.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * @param memberEmail 로그인정보, DB저장 데이터 비교
     * @return 회원정보 반환
     */
    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Optional<MemberEntity> byEmail = memberRepository.findByEmail(memberEmail);
        MemberEntity memberEntity = byEmail.orElseThrow(() -> new UsernameNotFoundException("회원가입이 필요합니다."));

        return User.builder()
                .username(memberEntity.getEmail())
                .password(memberEntity.getTextAuthenticationNumber())
                .roles(memberEntity.getRole().getValue())
                .build();
    }

}
