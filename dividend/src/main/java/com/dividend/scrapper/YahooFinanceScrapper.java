package com.dividend.scrapper;

import com.dividend.exception.implementation.scrapper.InvalidMonthException;
import com.dividend.exception.implementation.scrapper.TableNotFoundException;
import com.dividend.model.Company;
import com.dividend.model.Dividend;
import com.dividend.model.ScrapedResult;
import com.dividend.model.constants.Month;
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

import static com.dividend.model.constants.ScrapCompanyConstant.*;
import static com.dividend.model.constants.ScrapConstants.*;


@Component
public class YahooFinanceScrapper implements Scrapper {
    // Todo
    // 1. 예외 처리 핸들러 구현
    // 2. 예외 상황인 월 정보가 잘못된 경우

    @Override
    public ScrapedResult scrap(Company company) {
        ScrapedResult scrappedResult;

        long now = System.currentTimeMillis() / ONE_THOUSAND_MILLISECOND;
        String uri = String.format(REQUEST_URI, company.getTicker(), START_TIME, now);
        Element tbody = getDividendTable(uri);
        List<Dividend> dividends = collectDividend(tbody);
        scrappedResult = new ScrapedResult(company, dividends);

        return scrappedResult;
    }

    @Override
    public Company scrapCompanyByTicker(String ticker) {
        String uri = String.format(SUMMARY_URI, ticker, ticker);
        try {
            Document document = Jsoup.connect(uri).get();
            Element titleElement = document.getElementsByTag(H1_TAG).get(FIRST_H1_TAG_POSITION);
            String companyName = titleElement.text()
                    .split(OPEN_BRACKET)[FIRST_COMPANY_NAME_POSITION].trim();

            return new Company(ticker, companyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void validateDividendPage(Elements dividendPageElements) {
        if (ObjectUtils.isEmpty(dividendPageElements)) {
            throw new TableNotFoundException();
        }
    }

    private void validateMonth(int month) {
        if (month < JANUARY) {
            throw new InvalidMonthException();
        }
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
