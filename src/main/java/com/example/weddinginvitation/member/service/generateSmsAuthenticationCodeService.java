package com.example.weddinginvitation.member.service;

import java.util.Random;
import net.nurigo.sdk.message.model.Message;
import org.springframework.stereotype.Service;

@Service
public class generateSmsAuthenticationCodeService {

    public Message generateSmsAuthenticationCode(String phoneNumber) {

        Random rand = new Random();
        StringBuilder authKey = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            String ranAuthKey = Integer.toString(rand.nextInt(10));
            authKey.append(ranAuthKey);
        }

        Message message = new Message();
        message.setFrom("01097799391");
        message.setTo(phoneNumber);
        message.setSubject(String.valueOf(authKey));
        message.setText("[청첩장 커스터마이징 서비스] 입력하셔야할 인증번호는[" + authKey + "]입니다.");
        return message;
    }
}
