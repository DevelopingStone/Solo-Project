package com.example.weddinginvitation.member.controller;


import com.example.weddinginvitation.member.dto.MemberDto;
import com.example.weddinginvitation.member.entity.MemberEntity;
import com.example.weddinginvitation.member.service.MemberService;
import com.example.weddinginvitation.member.service.OauthService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private final sendSmsAuthenticationCodeController sendSMSAuthenticationCodeController;
    private final OauthService oauthService;
    private final MemberService memberService;

    /**
     * @param code 소셜로그인 인증코드, 액세스토큰 발급
     * @return 소셜로그인 멤버정보
     */
    @GetMapping("/kakaoLogin")
    public ResponseEntity<MemberEntity> kakaoLogin(@RequestParam(value = "code") String code) {

        String access_Token = oauthService.getAccessToken(code);
        logger.info("Access Token: {}", access_Token);

        HashMap<String, Object> userInfo = oauthService.getUserInfo(access_Token);
        logger.info("Nickname: {}", userInfo.get("nickname"));
        logger.info("Email: {}", userInfo.get("email"));

        MemberEntity memberEntity = memberService.kakaoLogin(userInfo);

        return ResponseEntity.ok(memberEntity);

    }

    /**
     * @param memberDto 핸드폰 인증번호 발송
     * @return 인증번호 (개발중에만 확인용으로 노출)
     */
    @PostMapping("/sendSmsAuthenticationCode")
    public ResponseEntity<MemberEntity> sendSmsAuthenticationCode(@RequestBody MemberDto memberDto) {
        String authenticationNumber = sendSMSAuthenticationCodeController.sendOne(memberDto.getPhoneNumber());
        MemberEntity memberEntity = memberService.sendSmsAuthenticationCode(memberDto, authenticationNumber);
        return ResponseEntity.ok(memberEntity);
    }

    /**
     * @param memberDto 핸드폰 인증번호 확인
     * @return 인증번호 확인 (개발중에만 확인용으로 노출)
     */
    @PostMapping("/sendSmsAuthenticationCodeCheck")
    public ResponseEntity<MemberEntity> sendSmsAuthenticationCodeCheck(@RequestBody MemberDto memberDto) {
        MemberEntity memberEntity = memberService.sendSmsAuthenticationCodeCheck(memberDto.getTextAuthenticationNumber());
        return ResponseEntity.ok(memberEntity);
    }

}
