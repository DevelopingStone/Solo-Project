package com.dividend.service.component;

import com.dividend.exception.implementation.company.AlreadyExistTickerException;
import com.dividend.exception.implementation.company.FailScrapCompanyException;
import com.dividend.exception.implementation.company.NoCompanyException;
import com.dividend.exception.implementation.company.TickerAbsentException;
import com.dividend.model.domain.Company;
import com.dividend.model.vo.ScrapedResult;
import com.dividend.persist.CompanyRepository;
import com.dividend.persist.DividendRepository;
import com.dividend.persist.entity.CompanyEntity;
import com.dividend.persist.entity.DividendEntity;
import com.dividend.service.module.Scrapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {
    // TODO
    // 1. Swagger
    // 2. Logback
    // 3. test code without springboot test especially mock or classic

    private final Scrapper yahooFinanceScrapper;
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    public Company save(String ticker) {
        if(ObjectUtils.isEmpty(ticker)){
            throw new TickerAbsentException();
        }

        boolean exists = this.companyRepository.existsByTicker(ticker);

        if (exists) {
            throw new AlreadyExistTickerException();
        }

        return this.storeCompanyAndDividend(ticker);
    }

    private Company storeCompanyAndDividend(String ticker) {
        Company company = this.yahooFinanceScrapper.scrapCompanyByTicker(ticker);
        if (ObjectUtils.isEmpty(company)) {
            throw new FailScrapCompanyException(ticker);
        }
        CompanyEntity companyEntity = this.companyRepository.save(
                new CompanyEntity(company));

        ScrapedResult scrapedResult = this.yahooFinanceScrapper.scrapDividendByCompany(company);
        List<DividendEntity> dividendEntities = scrapedResult
                                                .toDividendEntities(companyEntity.getId());

        this.dividendRepository.saveAll(dividendEntities);
        return company;
    }

    public Page<CompanyEntity> getAllCompany(Pageable pageable) {
        return this.companyRepository.findAll(pageable);
    }

    public List<String> getCompanyNamesByKeyword(String keyword) {
        Pageable limit = PageRequest.of(0, 10);
        List<CompanyEntity> companyEntities = this.companyRepository.findByNameStartingWithIgnoreCase(keyword, limit);
        return companyEntities.stream()
                .map(CompanyEntity::getName)
                .collect(Collectors.toList());
    }

    public String deleteCompany(String ticker) {
        CompanyEntity companyEntity = this.companyRepository.findByTicker(ticker)
                .orElseThrow(NoCompanyException::new);
        this.dividendRepository.deleteAllByCompanyId(companyEntity.getId());
        this.companyRepository.delete(companyEntity);

        return companyEntity.getName();
    }
}
