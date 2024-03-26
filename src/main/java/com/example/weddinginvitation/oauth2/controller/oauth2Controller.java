package com.example.weddinginvitation.oauth2.controller;


import com.example.weddinginvitation.oauth2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/member/*")
public class oauth2Controller {


    private final MemberService ms;

    @RequestMapping(value = "/kakaoLogin", method = RequestMethod.GET)
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
        System.out.println("#########" + code);

        // 위에서 만든 코드 아래에 코드 추가
        String access_Token = ms.getAccessToken(code);
        System.out.println("###access_Token#### : " + access_Token);

        return "member/testPage";
    }

}
