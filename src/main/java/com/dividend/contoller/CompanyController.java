package com.dividend.contoller;

import com.dividend.dto.AutoComplete;
import com.dividend.dto.CreateCompany;
import com.dividend.dto.DeleteCompany;
import com.dividend.model.vo.ScrapedResult;
import com.dividend.service.component.nonScheduled.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        return ResponseEntity.ok(AutoComplete.Response.from(
                this.companyService.recommendCompanyName(keyword)));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCompany(final Pageable pageable) {

        return ResponseEntity.ok(companyService.getAllCompany(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCompany(@RequestBody CreateCompany.Request request) {

        ScrapedResult scrapedResult = this.companyService.scrap(request.getTicker());


        return ResponseEntity.ok(CreateCompany.Response.from(
                this.companyService.storeCompanyAndDividend(scrapedResult)));
    }

    @DeleteMapping("/{ticker}")
    public ResponseEntity<?> deleteCompany(@PathVariable String ticker) {
        return ResponseEntity.ok(DeleteCompany.Response.from(this.companyService.deleteCompany(ticker)));
    }


}
