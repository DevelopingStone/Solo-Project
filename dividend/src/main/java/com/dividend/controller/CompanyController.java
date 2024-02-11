package com.dividend.controller;

import com.dividend.model.Company;
import com.dividend.model.ScrapedResult;
import com.dividend.service.CompanyService;
import com.dividend.service.YahooFinanceService;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
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

    private final YahooFinanceService yahooFinanceService ;
    private final CompanyService companyService ;

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/scrap")
    public ResponseEntity<?> scrap(@RequestBody Company company) {
        ScrapedResult scrap = yahooFinanceService.scrap(company);
        return ResponseEntity.ok().body(scrap);
    }

    @PostMapping("/scrapCompanyByTicker")
    public ResponseEntity<?> scrapCompanyByTicker(@RequestBody Company company) {
        Company result = yahooFinanceService.scrapCompanyByTicker(company.getTicker());
        return ResponseEntity.ok().body(result);
    }

    @PutMapping()
    public ResponseEntity<?> addCompany(@RequestBody Company company) {
        String ticker = company.getTicker().trim();
        if(ObjectUtils.isEmpty(ticker)){
            throw new RuntimeException("ticker is empty");
        }
        Company companyServiceSave = companyService.save(ticker);
        return ResponseEntity.ok().body(companyServiceSave);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCompany() {
        return ResponseEntity.ok().build();
    }

}

