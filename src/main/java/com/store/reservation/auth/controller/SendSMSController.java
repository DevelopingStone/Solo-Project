package com.store.reservation.auth.controller;


import com.store.reservation.auth.service.SendSmsService;
import com.store.reservation.member.dto.MemberDto;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendSMSController {

    private final DefaultMessageService messageService;
    private final SendSmsService sendSmsService;

    @Autowired
    public SendSMSController(SendSmsService sendSmsService) {
        this.sendSmsService = sendSmsService;
        this.messageService = NurigoApp.INSTANCE.initialize("NCSO8SAGQJZTJ7OP", "CAJJUMRR4VHB3ONPDYC7EY5FJ0KSUC8N",
                "https://api.coolsms.co.kr");
    }

    /**
     * @param phoneNumber 인증 SMS 발송
     * @return 인증코드 결과값
     * EX) http://localhost:8080/send-one
     */
    @PostMapping("/send-one")
    public String sendOne(@RequestBody String phoneNumber) {
        Message message = sendSmsService.sendSms(phoneNumber);
        this.messageService.sendOne(new SingleMessageSendingRequest(message));
        return message.getSubject();
    }
}
