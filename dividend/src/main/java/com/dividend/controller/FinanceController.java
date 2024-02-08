package com.dividend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    @GetMapping("dividend/{companyName}")
    public ResponseEntity<?> searchCompany(@PathVariable String companyName) {
        return ResponseEntity.ok().build();
    }

}
