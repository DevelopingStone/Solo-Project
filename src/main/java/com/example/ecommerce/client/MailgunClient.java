package com.example.ecommerce.client;


import com.example.ecommerce.client.mailgun.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailgunClient {

    @PostMapping("sandboxc12292b9eb87474eba8dd12f062aa729.mailgun.org/messages")
    Response sendEmail(@SpringQueryMap SendMailForm form);

}


