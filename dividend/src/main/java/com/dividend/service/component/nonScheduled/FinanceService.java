package com.dividend.service.component.nonScheduled;

import com.dividend.model.domain.Company;
import com.dividend.model.domain.Dividend;
import com.dividend.model.vo.ScrapedResult;
import com.dividend.persist.entity.CompanyEntity;
import com.dividend.service.module.company.CompanyModuleServiceImpl;
import com.dividend.service.module.dividend.DividendModuleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dividend.service.component.nonScheduled.constant.CacheKey.KEY_FINANCE;


@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {


    private final CompanyModuleServiceImpl companyModuleServiceImpl;
    private final DividendModuleServiceImpl dividendModuleServiceImpl;
    @Transactional
    @Cacheable(key = "#companyName", value = KEY_FINANCE)
    public ScrapedResult getDividendByCompanyName(String companyName) {
        log.info("요청받은 "+companyName+ " 회사의 배당금 조회 서비스 시작");

        log.info("요청받은 "+companyName+ " 회사를 데이터베이스에서 조회 시작");
        CompanyEntity company = companyModuleServiceImpl.findCompanyByCompanyName(companyName);
        log.info("요청받은 "+companyName+ " 회사를 데이터베이스에서 조회 끝");

        log.info("요청받은 "+companyName+ " 회사의 배당금 조회 시작");
        List<Dividend> dividendList = dividendModuleServiceImpl.getDividendsByCompany(company.getId());
        log.info("요청받은 "+companyName+ " 회사의 배당금 조회 끝");

        log.info("요청받은 "+companyName+ " 회사의 배당금 조회 서비스 끝");
        return new ScrapedResult(
                new Company(company.getTicker(),
                        company.getName()),
                        dividendList);
    }

}
