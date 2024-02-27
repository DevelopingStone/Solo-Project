package com.dividend.contoller;

import com.dividend.dto.ScrapedResultDto;
import com.dividend.service.component.nonScheduled.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
@AllArgsConstructor
public class DividendController {

    private final FinanceService financeService;

    @GetMapping("/dividend/{companyName}")
    public ResponseEntity<?> searchFinance(@PathVariable String companyName) {
        return ResponseEntity.ok(ScrapedResultDto.from(
                financeService.getDividendByCompanyName(companyName)
        ));
    }
}
