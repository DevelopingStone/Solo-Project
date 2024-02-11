package com.dividend.service;

import com.dividend.entity.CompanyEntity;
import com.dividend.entity.DividendEntity;
import com.dividend.model.Company;
import com.dividend.model.ScrapedResult;
import com.dividend.repository.CompanyRepository;
import com.dividend.repository.DividendRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
public class CompanyService {

    private final Scraper yahooFinanceScraper;
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    public Company save(String ticker) {
        if(companyRepository.existsByTicker(ticker)){
            throw new RuntimeException("already exists ticker -> : " + ticker);
        }

        return storeCompanyAndDividend(ticker);

    }

    private Company storeCompanyAndDividend(String ticker) {
        Company company = yahooFinanceScraper.scrapCompanyByTicker(ticker);
        if (ObjectUtils.isEmpty(company)) {
            throw new RuntimeException("failed to scrap ticker -> " + ticker);
        }
        ScrapedResult scrapResult = yahooFinanceScraper.scrap(company);

        CompanyEntity companyEntity = CompanyEntity.builder()
                .name(company.getName())
                .ticker(company.getTicker())
                .build();

        CompanyEntity saveCompanyEntity = companyRepository.save(companyEntity);

        List<DividendEntity> collect = scrapResult.getDividendEntities().stream()
                .map(e -> new DividendEntity(saveCompanyEntity.getId(), e))
                .collect(Collectors.toList());

        this.dividendRepository.saveAll(collect);
        return company;
    }
}
