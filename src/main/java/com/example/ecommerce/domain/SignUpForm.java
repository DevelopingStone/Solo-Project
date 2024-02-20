package com.example.ecommerce.domain;

import java.time.LocalDate;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class SignUpForm {

    private String email;
    private String name;
    private String password;
    private LocalDate birth;
    private String phone;

}
