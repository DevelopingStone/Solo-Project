package com.example.ecommerce.service;

import com.example.ecommerce.client.MailgunClient;
import com.example.ecommerce.client.mailgun.SendMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final MailgunClient mailgunClient;

    public String sendEmail() {

        SendMailForm form = SendMailForm.builder()
                .from("zerobase-test@my.com")
                .to("kangrack333@gmail.com")
                .subject("Test email from zero-base")
                .text("my text")
                .build();

          return mailgunClient.sendEmail(form).getBody();
    }

}

