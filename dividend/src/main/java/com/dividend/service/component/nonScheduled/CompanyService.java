package com.dividend.service.component.nonScheduled;

import com.dividend.dto.AutoCompleteDto;
import com.dividend.dto.CompanyDto;
import com.dividend.exception.implementation.company.AlreadyExistTickerException;
import com.dividend.exception.implementation.company.FailScrapCompanyException;
import com.dividend.exception.implementation.company.NoCompanyException;
import com.dividend.exception.implementation.company.TickerAbsentException;
import com.dividend.model.domain.Company;
import com.dividend.model.vo.ScrapedResult;
import com.dividend.persist.CompanyRepository;
import com.dividend.persist.DividendRepository;
import com.dividend.persist.entity.CompanyEntity;
import com.dividend.service.component.scheduled.constant.CacheKey;
import com.dividend.service.module.Scrapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dividend.service.component.nonScheduled.constant.PageableConstant.NUMBER_OF_CONTENT_PER_PAGE;
import static com.dividend.service.component.nonScheduled.constant.PageableConstant.NUMBER_OF_PAGE;

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
    private final CacheManager redisCacheManager;

    public CompanyDto save(String ticker) {
        if(ObjectUtils.isEmpty(ticker)){
            throw new TickerAbsentException();
        }

        boolean exists = this.companyRepository.existsByTicker(ticker);

        if (exists) {
            throw new AlreadyExistTickerException();
        }

        return this.storeCompanyAndDividend(ticker);
    }

    private CompanyDto storeCompanyAndDividend(String ticker) {
        Company company = this.yahooFinanceScrapper.scrapCompanyByTicker(ticker);
        if (ObjectUtils.isEmpty(company)) {
            throw new FailScrapCompanyException(ticker);
        }

        CompanyEntity companyEntity = this.companyRepository.save(
                new CompanyEntity(company));

        ScrapedResult scrapedResult = this.yahooFinanceScrapper.scrapDividendByCompany(company);
        this.dividendRepository.saveAll(scrapedResult.toDividendEntities(companyEntity.getId()));

        return CompanyDto.fromDomainModel(company);
    }

    public List<CompanyDto> getAllCompany(Pageable pageable) {
        return this.companyRepository.findAll(pageable).stream()
                .map(CompanyDto::fromEntity)
                .collect(Collectors.toList());
    }

    public AutoCompleteDto getCompanyNamesByKeyword(String keyword) {
        Pageable limit = PageRequest.of(NUMBER_OF_PAGE, NUMBER_OF_CONTENT_PER_PAGE);
        return AutoCompleteDto.from(this.companyRepository.findByNameStartingWithIgnoreCase(keyword, limit));
    }

    public CompanyDto deleteCompany(String ticker) {
        CompanyEntity companyEntity = this.companyRepository.findByTicker(ticker.trim())
                .orElseThrow(NoCompanyException::new);
        this.dividendRepository.deleteAllByCompanyId(companyEntity.getId());
        this.companyRepository.delete(companyEntity);
        this.clearFinanceCache(companyEntity.getName());
        return CompanyDto.fromDomainModel(new Company(companyEntity.getTicker(), companyEntity.getName()));
    }
    private void clearFinanceCache(String companyName) {
        Objects.requireNonNull(this.redisCacheManager.getCache(CacheKey.KEY_FINANCE)).evict(companyName);
    }
}
