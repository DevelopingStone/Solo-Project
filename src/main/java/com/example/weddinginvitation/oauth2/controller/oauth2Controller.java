package com.example.weddinginvitation.oauth2.controller;


import com.example.weddinginvitation.oauth2.service.MemberService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/member")
public class oauth2Controller {


    private final MemberService ms;

    @GetMapping("/kakaoLogin")
    public String kakaoLogin(@RequestParam(value = "code") String code) {
        System.out.println("code : " + code);
        String access_Token = ms.getAccessToken(code);

        HashMap<String, Object> userInfo = ms.getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        System.out.println("###email#### : " + userInfo.get("email"));

        return null;
    }

}
