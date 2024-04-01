package com.example.weddinginvitation.member.controller;


import com.example.weddinginvitation.member.service.generateSmsAuthenticationCodeService;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class generateSmsAuthenticationCodeController {

    private final DefaultMessageService messageService;
    private final generateSmsAuthenticationCodeService generateSmsAuthenticationCodeService;

    @Autowired
    public generateSmsAuthenticationCodeController(
            generateSmsAuthenticationCodeService generateSmsAuthenticationCodeService) {
        this.generateSmsAuthenticationCodeService = generateSmsAuthenticationCodeService;
        this.messageService = NurigoApp.INSTANCE.initialize("NCSO8SAGQJZTJ7OP", "CAJJUMRR4VHB3ONPDYC7EY5FJ0KSUC8N",
                "https://api.coolsms.co.kr");
    }

    /**
     * @param phoneNumber 인증 SMS 발송
     * @return 인증코드 결과값
     */
    @PostMapping("/send-one")
    public String sendOne(@RequestBody String phoneNumber) {
        Message message = generateSmsAuthenticationCodeService.generateSmsAuthenticationCode(phoneNumber);
        this.messageService.sendOne(new SingleMessageSendingRequest(message));
        return message.getSubject();
    }
}


