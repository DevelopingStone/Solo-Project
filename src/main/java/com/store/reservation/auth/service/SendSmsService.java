package com.store.reservation.auth.service;

import com.store.reservation.member.dto.MemberDto;
import java.util.Random;
import net.nurigo.sdk.message.model.Message;
import org.springframework.stereotype.Service;

@Service
public class SendSmsService {

    public Message sendSms(MemberDto phoneNum) {

        Random rand = new Random();
        StringBuilder authKey = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            String ranAuthKey = Integer.toString(rand.nextInt(10));
            authKey.append(ranAuthKey);
        }

        Message message = new Message();
        message.setFrom("01097799391");
        message.setTo(phoneNum.getPhoneNumber());
        message.setSubject(String.valueOf(authKey));
        message.setText("[Ori-Ticket] 입력하셔야할 인증번호는[" + authKey + "]입니다.");
        return message;
    }
}
