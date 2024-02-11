package com.dividend.controller;

import com.dividend.model.Company;
import com.dividend.service.YahooFinanceService;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    YahooFinanceService yahooFinanceService ;

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<?> searchCompany(@RequestBody Company company) {
        yahooFinanceService.scrap(company);
        return ResponseEntity.ok().body(company);
    }

    @PutMapping()
    public ResponseEntity<?> addCompany() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCompany() {
        return ResponseEntity.ok().build();
    }

}
