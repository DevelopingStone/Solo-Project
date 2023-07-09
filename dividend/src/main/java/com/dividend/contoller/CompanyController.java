package com.dividend.contoller;

import com.dividend.dto.CreateCompany;
import com.dividend.model.domain.Company;
import com.dividend.persist.entity.CompanyEntity;
import com.dividend.service.component.CompanyService;
import com.dividend.service.component.scheduler.constant.CacheKey;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CacheManager redisCacheManager;

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        List<String> autocomplete = this.companyService.getCompanyNamesByKeyword(keyword);
        return ResponseEntity.ok(autocomplete);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCompany(final Pageable pageable) {
        Page<CompanyEntity> companies = companyService.getAllCompany(pageable);
        return ResponseEntity.ok(companies);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCompany(@RequestBody CreateCompany.Request request) {

        Company company = this.companyService.save(request.getTicker());
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/{ticker}")
    public ResponseEntity<?> deleteCompany(@PathVariable String ticker) {
        String companyName = this.companyService.deleteCompany(ticker);
        this.clearFinanceCache(companyName);
        return ResponseEntity.ok(companyName);
    }

    public void clearFinanceCache(String companyName) {
        Objects.requireNonNull(this.redisCacheManager.getCache(CacheKey.KEY_FINANCE)).evict(companyName);
    }

}
