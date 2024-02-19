package com.example.ecommerce.controller;


import com.example.ecommerce.service.EmailSendService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final EmailSendService emailSendService;

    @GetMapping
    public Response sendTestEmail(){
        return emailSendService.sendEmail();
    }

}
