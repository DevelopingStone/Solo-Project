package com.example.weddinginvitation.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class ChatController {

    @GetMapping(value = "/chat", produces = MediaType.TEXT_HTML_VALUE + "; charset=UTF-8")
    public String chatGET(){
        log.info("@ChatController, chat GET()");
        return "chater";
    }
}
