package com.dividend.service.module.member;

import com.dividend.exception.implementation.member.AlreadyExistsUserException;
import com.dividend.exception.implementation.member.NoUsernameException;
import com.dividend.exception.implementation.member.NotMatchPasswordException;
import com.dividend.exception.implementation.member.UserNotFoundException;
import com.dividend.model.domain.Member;
import com.dividend.persist.entity.MemberEntity;
import com.dividend.persist.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements UserDetailsService, MemberService{

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        return this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public MemberEntity register(Member.SignUp member){
        boolean exists = this.memberRepository.existsByUsername(member.getUsername());
        if(exists){
            throw new AlreadyExistsUserException();
        }

        member.setPassword(this.passwordEncoder.encode(member.getPassword()));

        return this.memberRepository.save(member.toEntity());
    }

    public MemberEntity authenticate(Member.SignIn member){
        MemberEntity user = this.memberRepository.findByUsername(member.getUsername())
                .orElseThrow(NoUsernameException::new);

        if(!this.passwordEncoder.matches(member.getPassword(), user.getPassword())){
            throw new NotMatchPasswordException();
        }
        return user;
    }
}
