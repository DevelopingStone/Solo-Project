package com.dividend.service.component.nonScheduled;

import com.dividend.dto.AutoCompleteDto;
import com.dividend.dto.CompanyDto;
import com.dividend.exception.implementation.company.AlreadyExistTickerException;
import com.dividend.model.domain.Company;
import com.dividend.model.vo.ScrapedResult;
import com.dividend.persist.entity.CompanyEntity;
import com.dividend.service.component.nonScheduled.constant.CacheKey;
import com.dividend.service.module.company.CompanyModuleServiceImpl;
import com.dividend.service.module.dividend.DividendModuleServiceImpl;
import com.dividend.service.module.scrapper.Scrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CompanyService {
    // TODO
    // 1. Swagger
    // 2. Logback
    // 3. test code without springboot test especially mock or classic

    private final Scrapper yahooFinanceScrapper;
    private final CompanyModuleServiceImpl companyService;
    private final DividendModuleServiceImpl dividendModuleService;
    private final CacheManager redisCacheManager;

    public ScrapedResult scrap(String ticker) {
        log.info("스크랩 시작");
        Company company = yahooFinanceScrapper.scrapCompanyByTicker(ticker);
        log.info("스크랩 정상 종료");
        return this.yahooFinanceScrapper.scrapDividendByCompany(company);

    }

    @Transactional
    public Company storeCompanyAndDividend(ScrapedResult scrapedResult) {
        log.info("회사정보와 회사의 배당금 정보 저장 시작");
        boolean exists = companyService.checkExistenceByTicker(scrapedResult.getCompany().getTicker());
        if (exists) {
            throw new AlreadyExistTickerException();
        }
        CompanyEntity companyEntity = this.companyService.save(scrapedResult.getCompany());

        dividendModuleService.saveDividends(
                scrapedResult.toDividendEntities(companyEntity.getId()));
        log.info("회사정보와 회사의 배당금 정보 저장 정상 종료");
        return scrapedResult.getCompany();
    }

    public Page<CompanyEntity> getAllCompany(Pageable pageable) {
        log.info("저장된 모든 회사 정보 조회");
        return companyService.findAllCompany(pageable);
    }

    public AutoCompleteDto recommendCompanyName(String keyword) {
        log.info("유사한 ticker를 가진 회사 목록 조회");
        return AutoCompleteDto.from(companyService.getCompanyNamesByKeyword(keyword));
    }

    @Transactional
    public CompanyDto deleteCompany(String ticker) {
        log.info(ticker + "에 해당하는 회사 정보와 배당금 정보 삭제 시작");
        CompanyEntity companyEntity = this.companyService.findCompanyByTicker(ticker.trim());
        this.dividendModuleService.deleteAllByCompanyId(companyEntity.getId());
        this.companyService.delete(companyEntity);
        this.clearFinanceCache(companyEntity.getName());
        log.info(ticker + "에 해당하는 회사 정보와 배당금 정보 삭제 정상 종료");
        return CompanyDto.fromDomainModel(new Company(companyEntity.getTicker(), companyEntity.getName()));
    }

    private void clearFinanceCache(String companyName) {
        log.info("캐시 서버에서 "+companyName+" 정보를 삭제 시작");
        Objects.requireNonNull(this.redisCacheManager.getCache(CacheKey.KEY_FINANCE)).evict(companyName);
        log.info("캐시 서버에서 "+companyName+" 정보를 삭제 완료");
    }
}
