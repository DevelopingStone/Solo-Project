package com.dividend.service.component.scheduler;

import com.dividend.model.domain.Company;
import com.dividend.model.vo.ScrapedResult;
import com.dividend.persist.CompanyRepository;
import com.dividend.persist.DividendRepository;
import com.dividend.persist.entity.CompanyEntity;
import com.dividend.persist.entity.DividendEntity;
import com.dividend.service.component.scheduler.constant.CacheKey;
import com.dividend.service.module.Scrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@EnableCaching
@RequiredArgsConstructor
public class ScrapperScheduledService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    private final Scrapper yahooFinanceScrapper;
    private static final Long THREAD_SLEEP_TIME = 3000L;

    @CacheEvict(value = CacheKey.KEY_FINANCE, allEntries = true)
    @Scheduled(cron = "${scheduler.scrap.yahoo}")
    public void yahooFinanceScheduler() {
        log.info("scrapping scheduler is started");

        List<CompanyEntity> companies = this.companyRepository.findAll();

        for (CompanyEntity companyEntity : companies) {
            log.info("scrapping scheduler is started -> " + companyEntity.getName());
            ScrapedResult scrapedResult =
                    this.yahooFinanceScrapper.scrapDividendByCompany(
                            new Company(companyEntity.getTicker(), companyEntity.getName())
                    );
            saveDividendWithScheduling(scrapedResult, companyEntity.getId());
            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void saveDividendWithScheduling(ScrapedResult scrapedResult, Long companyId) {
        scrapedResult.getDividends().stream().map(dividend -> new DividendEntity(companyId, dividend)).forEach(dividendEntity -> {
            boolean exists = checkRedundancy(dividendEntity);
            if (!exists) {
                this.dividendRepository.save(dividendEntity);
            }
        });
    }

    public boolean checkRedundancy(DividendEntity dividendEntity) {
        return this.dividendRepository.existsByCompanyIdAndDate(dividendEntity.getCompanyId(), dividendEntity.getDate());
    }


}


