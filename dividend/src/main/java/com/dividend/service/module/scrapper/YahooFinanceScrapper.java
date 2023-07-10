package com.dividend.service.module.scrapper;

import com.dividend.exception.implementation.company.TickerAbsentException;
import com.dividend.exception.implementation.scrapper.CompanyNameNotFoundException;
import com.dividend.exception.implementation.scrapper.InvalidMonthException;
import com.dividend.exception.implementation.scrapper.NoH1TagExistenceException;
import com.dividend.exception.implementation.scrapper.TableNotFoundException;
import com.dividend.model.domain.Company;
import com.dividend.model.domain.Dividend;
import com.dividend.model.vo.ScrapedResult;
import com.dividend.service.module.scrapper.constant.Month;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.dividend.service.module.scrapper.constant.ScrapCompanyConstant.*;
import static com.dividend.service.module.scrapper.constant.ScrapConstants.*;

@Slf4j
@Component
public class YahooFinanceScrapper implements Scrapper {

    @Override
    public ScrapedResult scrapDividendByCompany(Company company) {
        log.info("배당금 스크랩 시작!");
        long now = System.currentTimeMillis() / ONE_THOUSAND_MILLISECOND;
        String uri = String.format(REQUEST_URI, company.getTicker(), START_TIME, now);
        Element tbody = getDividendTable(uri);
        List<Dividend> dividendList = collectDividend(tbody);
        log.info("배당금 스크랩 정상 종료!");
        return new ScrapedResult(company, dividendList);
    }

    @Override
    public Company scrapCompanyByTicker(String ticker) {
        log.info("ticker을 이용한 회사 이름 스크랩 시작");
        String trimmedTicker = ticker.trim();
        checkTickerExistence(trimmedTicker);
        String uri = String.format(SUMMARY_URI, trimmedTicker, trimmedTicker);
        String companyName;
        try {
            Document document = Jsoup.connect(uri).get();
            Elements h1Tags = document.getElementsByTag(H1_TAG);
            validateExistenceH1Tag(h1Tags);
            Element titleElement = h1Tags.get(FIRST_H1_TAG_POSITION);
            companyName = titleElement.text()
                    .split(OPEN_BRACKET)[FIRST_COMPANY_NAME_POSITION].trim();
        } catch (IOException e) {
            log.info("요청하신 URI가 잘못되었습니다!");
            throw new RuntimeException(e);
        }
        validateCompanyName(companyName);
        log.info("ticker을 이용한 회사 이름 스크랩 정상 종료");
        return new Company(ticker, companyName);
    }

    private void checkTickerExistence(String ticker){
        log.info("ticker 유효성 검사 시작");
        if (ObjectUtils.isEmpty(ticker)) {
            log.info("ticker가 존재하지 않습니다!!");
            throw new TickerAbsentException();
        }
        log.info("ticker 유효성 검사 정상 종료");
    }

    private void validateExistenceH1Tag(Elements h1Tags){
        log.info("페이지 내 h1 태그 유효성 검사 시작");
        if(ObjectUtils.isEmpty(h1Tags)){
            log.info("페이지 내 h1 태그 없음!!");
            throw new NoH1TagExistenceException();
        }
        log.info("페이지 내 h1 태그 유효성 검사 정상 종료");
    }

    private void validateCompanyName(String companyName) {
        log.info("스크랩한 회사 이름 유효성 검사 시작");
        if (ObjectUtils.isEmpty(companyName)) {
            log.info("스크랩한 결과에 회사 이름이 없습니다!!");
            throw new CompanyNameNotFoundException();
        }
        log.info("스크랩한 회사 이름 유효성 검사 정상 종료");
    }

    private void validateDividendPage(Elements dividendPageElements) {
        log.info("페이지 내 테이블 요소 존재 유무 검사 시작");
        if (ObjectUtils.isEmpty(dividendPageElements)) {
            log.info("페이지 내 테이블 요소가 없습니다.");
            throw new TableNotFoundException();
        }
        log.info("페이지 내 테이블 요소 존재 유무 검사 정상 종료");
    }

    private void validateMonth(int month) {
        log.info("스크랩한 배당금 데이터의 날짜 중 월 정보 유효성 검사 시작");
        if (month < JANUARY) {
            log.info("스크랩한 배당금 데이터의 날짜 중 월 정보 유효성 문제 발생!");
            throw new InvalidMonthException();
        }
        log.info("스크랩한 배당금 데이터의 날짜 중 월 정보 유효성 검사 정상 종료");
    }

    private Dividend parseToDividend(String dividendTableRowText) {
        String[] DividendData = dividendTableRowText.split(BLANK);

        int month = Month.MonthNameToMonthNumber(DividendData[MONTH_POSITION]);
        int day = Integer.parseInt(DividendData[DAY_POSITION]
                .replace(COMMA, WHITE_SPACE));
        int year = Integer.parseInt(DividendData[YEAR_POSITION]);
        String dividend = DividendData[DIVIDEND_POSITION];

        validateMonth(month);

        return new Dividend(
                LocalDateTime.of(year, month, day, EMPTY_HOUR, EMPTY_MINUTE),
                dividend);
    }

    private Element getDividendTable(String uri) {
        Element tableBody;
        try {
            Document document = Jsoup.connect(uri).get();
            Elements dividendPageElements = document.getElementsByAttributeValue(
                    DIVIDEND_TABLE_ATTRIBUTE_NAME, DIVIDEND_TABLE_ATTRIBUTE_VALUE);

            validateDividendPage(dividendPageElements);

            Element table = dividendPageElements.get(FIRST_TABLE);
            tableBody = table.children().get(TBODY);

        } catch (IOException e) {
            log.info("요청하신 URI가 잘못되었습니다!");
            throw new RuntimeException(e);
        }

        return tableBody;
    }

    private List<Dividend> collectDividend(Element tableBody) {
        List<Dividend> dividends = new ArrayList<>();
        for (Element tableRowContent : tableBody.children()) {
            String rowText = tableRowContent.text();
            if (!rowText.endsWith(DIVIDEND)) {
                continue;
            }
            Dividend dividend = parseToDividend(rowText);
            dividends.add(dividend);
        }
        return dividends;
    }


}
