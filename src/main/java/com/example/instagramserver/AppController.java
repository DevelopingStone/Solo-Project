package com.example.instagramserver;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class AppController {

  @GetMapping
  public String hello() {
    return "Hello, every one";
  }

}
