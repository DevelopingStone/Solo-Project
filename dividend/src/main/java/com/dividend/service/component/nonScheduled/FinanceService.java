package com.dividend.service.component;

import com.dividend.exception.implementation.company.NoCompanyException;
import com.dividend.model.domain.Company;
import com.dividend.model.domain.Dividend;
import com.dividend.model.vo.ScrapedResult;
import com.dividend.persist.CompanyRepository;
import com.dividend.persist.DividendRepository;
import com.dividend.persist.entity.CompanyEntity;
import com.dividend.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.dividend.service.component.scheduler.constant.CacheKey.KEY_FINANCE;

@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {


    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    @Cacheable(key = "#companyName", value = KEY_FINANCE)
    public ScrapedResult getDividendByCompanyName(String companyName) {

        CompanyEntity company = companyRepository.findByName(companyName)
                .orElseThrow(NoCompanyException::new);

        List<DividendEntity> dividendEntities = dividendRepository.findAllByCompanyId(company.getId());

        List<Dividend> dividends = dividendEntities.stream()
                .map(dividendEntity -> new Dividend(dividendEntity.getDate(),
                        dividendEntity.getDividend()))
                .collect(Collectors.toList());

        return new ScrapedResult(new Company(company.getTicker(), company.getName()),
                dividends);
    }

}
