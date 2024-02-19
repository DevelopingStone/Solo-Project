package com.example.ecommerce.client.service;

import com.example.ecommerce.config.FeignConfig;
import com.example.ecommerce.service.EmailSendService;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FeignConfig.class)
class EmailSendServiceTest {


    @Autowired
    private EmailSendService emailSendService;


    @Test
    public void EmailTest() {
        Response response = emailSendService.sendEmail();

    }


}