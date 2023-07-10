package com.dividend.service.module;

import com.dividend.exception.AbstractException;
import com.dividend.exception.implementation.company.TickerAbsentException;
import com.dividend.exception.implementation.scrapper.NoH1TagExistenceException;
import com.dividend.exception.implementation.scrapper.TableNotFoundException;
import com.dividend.model.domain.Company;
import com.dividend.service.module.scrapper.YahooFinanceScrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YahooFinanceScrapperTest {

    private YahooFinanceScrapper yahooFinanceScrapper;

    @BeforeEach
    void setUp() {
        yahooFinanceScrapper = new YahooFinanceScrapper();
    }

    @Test
    @DisplayName("ticker 존재 - scrap 성공")
    void success_ScrapDividendByCompany() {
        // given
        String ticker = "COKE";
        Company company = new Company(ticker, "");

        // when
        Company expectedCompany = yahooFinanceScrapper.scrapCompanyByTicker(ticker);
        // then
        assertEquals(expectedCompany.getTicker(), ticker);
    }

    @Test
    @DisplayName("ticker가 없는 경우 - scrap 실패")
    void fail_ScrapDividendByCompany_NotExistsTicker() {
        // given
        String ticker = "NONE";
        Company company = new Company(ticker, "");
        // when

        AbstractException abstractException = assertThrows(AbstractException.class, () -> yahooFinanceScrapper.scrapDividendByCompany(company));
        // then
        assertEquals(abstractException.getClass(), TableNotFoundException.class);
    }

    @Test
    void success_ScrapCompanyByTicker() {
        // given
        String ticker = "MMM";
        String name = "3M Company";
        // when
        Company expectedCompany = yahooFinanceScrapper.scrapCompanyByTicker(ticker);
        // then
        assertEquals(expectedCompany.getTicker(), ticker);
        assertEquals(expectedCompany.getName(), name);
    }

    @Test
    @DisplayName("일치하는 ticker가 없는 경우 - 회사 이름 스크랩 실패")
    void fail_ScrapCompanyByTicker_NoMatchTicker() {
        // given
        String ticker = "NONE";

        // when
        AbstractException abstractException =
                assertThrows(AbstractException.class, () -> yahooFinanceScrapper.scrapCompanyByTicker(ticker));
        // then
        assertEquals(abstractException.getClass(), NoH1TagExistenceException.class);

    }

    @Test
    @DisplayName("ticker가 없는 경우 - 회사 이름 스크랩 실패")
    void fail_ScrapCompanyByTicker_TickerAbsence() {
        // given
        String ticker = "";

        // when
        AbstractException abstractException =
                assertThrows(AbstractException.class, () -> yahooFinanceScrapper.scrapCompanyByTicker(ticker));
        // then
        assertEquals(abstractException.getClass(), TickerAbsentException.class);

    }


}