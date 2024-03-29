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
    private final OauthService oauthService;
    private final MemberService memberService;
    private HashMap<String, Object> userInfo;

    /**
     * @param code 소셜로그인 인증코드 전달
     * @return 액세스토큰으로 전달받은 개인정보 리턴 값
     */
    @GetMapping("/kakaoLogin")
    public ResponseEntity<HashMap<String, Object>> kakaoLogin(@RequestParam(value = "code") String code) {
        String access_Token = oauthService.getAccessToken(code);
        logger.info("Access Token: {}", access_Token);

        HashMap<String, Object> userInfo = oauthService.getUserInfo(access_Token);
        logger.info("Nickname: {}", userInfo.get("nickname"));
        logger.info("Email: {}", userInfo.get("email"));
        this.userInfo = userInfo;

        return ResponseEntity.ok(userInfo);
    }

    /**
     * @param memberDto 멤버 회원가입
     * @return 회원가입 결과값
     */
    @PostMapping("/singUp")
    public ResponseEntity<MemberEntity> singUp(@RequestBody MemberDto memberDto) {
        MemberEntity memberEntity = memberService.singUp(memberDto, userInfo);
        return ResponseEntity.ok(memberEntity);
    }




}
