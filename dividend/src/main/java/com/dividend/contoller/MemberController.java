package com.dividend.contoller;

import com.dividend.model.domain.Member;
import com.dividend.persist.entity.MemberEntity;
import com.dividend.security.TokenProvider;
import com.dividend.service.module.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Member.SignUp request){
        MemberEntity registeredMember = this.memberServiceImpl.register(request);
        return ResponseEntity.ok(registeredMember);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Member.SignIn request){

        MemberEntity member = this.memberServiceImpl.authenticate(request);
        String token = this.tokenProvider.generateToken(member.getUsername(), member.getRoles());
        return ResponseEntity.ok(token);
    }

}
