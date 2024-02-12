package com.dividend.service.component.nonScheduled;

import com.dividend.model.domain.Company;
import com.dividend.model.domain.Dividend;
import com.dividend.model.vo.ScrapedResult;
import com.dividend.persist.entity.CompanyEntity;
import com.dividend.persist.entity.DividendEntity;
import com.dividend.service.module.company.CompanyModuleServiceImpl;
import com.dividend.service.module.dividend.DividendModuleServiceImpl;
import com.dividend.service.module.scrapper.YahooFinanceScrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.dividend.service.component.nonScheduled.constant.PageableConstant.NUMBER_OF_CONTENT_PER_PAGE;
import static com.dividend.service.component.nonScheduled.constant.PageableConstant.NUMBER_OF_PAGE;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    YahooFinanceScrapper yahooFinanceScrapper;

    @Mock
    CompanyModuleServiceImpl companyModuleService;

    @Mock
    DividendModuleServiceImpl dividendModuleService;

    @InjectMocks
    CompanyService companyService;

    List<DividendEntity> makeDividendEntities(){
        String dividend = "1.175";
        LocalDateTime dateTime = LocalDateTime.of(2017, 5, 17, 0, 0);
        List<DividendEntity> dividendEntities = new ArrayList<>();
        dividendEntities.add(new DividendEntity(0L,1L,dateTime, dividend));
        dividendEntities.add(new DividendEntity(1L,1L,dateTime.plusMonths(3), dividend));
        dividendEntities.add(new DividendEntity(2L,1L,dateTime.plusMonths(3), dividend));
        dividendEntities.add(new DividendEntity(3L,1L,dateTime.plusMonths(3), dividend));
        dividendEntities.add(new DividendEntity(4L,1L,dateTime.plusMonths(3), dividend));
        dividendEntities.add(new DividendEntity(5L,1L,dateTime.plusMonths(3), dividend));
        return dividendEntities;
    }
    List<Dividend> makeDividends(){
        String dividend = "1.175";
        LocalDateTime dateTime = LocalDateTime.of(2017, 5, 17, 0, 0);
        List<Dividend> dividendEntities = new ArrayList<>();
        dividendEntities.add(new Dividend(dateTime, dividend));
        dividendEntities.add(new Dividend(dateTime.plusMonths(3), dividend));
        dividendEntities.add(new Dividend(dateTime.plusMonths(3), dividend));
        dividendEntities.add(new Dividend(dateTime.plusMonths(3), dividend));
        dividendEntities.add(new Dividend(dateTime.plusMonths(3), dividend));
        dividendEntities.add(new Dividend(dateTime.plusMonths(3), dividend));
        return dividendEntities;
    }


    @Test
    void success_scrap() {
        // given
        String ticker = "MMM";
        String name = "3M Company";
        Company company = new Company(ticker, name);
        List<Dividend> dividends = makeDividends();
        given(yahooFinanceScrapper.scrapCompanyByTicker(anyString()))
                .willReturn(company);
        given(yahooFinanceScrapper.scrapDividendByCompany(company))
                .willReturn(new ScrapedResult(company, dividends));

        // when
        ScrapedResult scrapedResult = companyService.scrap(ticker);

        // then
        assertEquals(scrapedResult.getCompany(), company);
        assertArrayEquals(scrapedResult.getDividends().toArray(Object[]::new),
                dividends.toArray(Object[]::new));
    }

    @Test
    void success_storeCompanyAndDividend() {
        // given
        String ticker = "MMM";
        String name = "3M Company";
        Company company = new Company(ticker, name);
        List<Dividend> dividends = makeDividends();
        ScrapedResult scrapedResult = new ScrapedResult(company, dividends);

        given(companyModuleService.save(any()))
                .willReturn(new CompanyEntity(1L, company.getTicker(), company.getName()));
        // when
        Company expectedCompany = companyService.storeCompanyAndDividend(scrapedResult);
        // then
        assertEquals(expectedCompany.getName(), company.getName());
        assertEquals(expectedCompany.getTicker(), company.getTicker());
    }

    @Test
    void success_getAllCompany() {
        // given
        String name1 = "3M Company";
        String name2 = "NIKE .Inc";
        List<CompanyEntity> companyEntities = new ArrayList<>();
        companyEntities.add(new CompanyEntity(0L, "MMM", name1));
        companyEntities.add(new CompanyEntity(1L, "NKE", name2));

        Page<CompanyEntity> companyEntityPage = new PageImpl<>(companyEntities);
        given(companyModuleService.findAllCompany(any()))
                .willReturn(companyEntityPage);
        Pageable limit = PageRequest.of(NUMBER_OF_PAGE, NUMBER_OF_CONTENT_PER_PAGE);
        // when
        Page<CompanyEntity> allCompany = companyService.getAllCompany(limit);
        // then
        assertEquals(allCompany.getSize(), companyEntityPage.getSize());
    }





}