package com.dividend.service;

import com.dividend.config.Months;
import com.dividend.model.Company;
import com.dividend.model.Dividend;
import com.dividend.model.ScrapedResult;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class YahooFinanceService implements Scraper {

    private static final String STATIC_URL = "https://finance.yahoo.com/quote/%s/history?period1=%d&period2=%d&interval=1mo";
    private static final String SUMMARY_URL = "https://finance.yahoo.com/quote/%s?p=%s";
    private static final long START_TIME = 86400;
    ScrapedResult scrapResult;

    public ScrapedResult scrap(Company company) {

        scrapResult.setCompany(company);

        try {
            long now = System.currentTimeMillis() / 1000;
            final String URL = String.format(STATIC_URL, company.getTicker(), START_TIME, now);

            Connection connection = Jsoup.connect(URL);
            Document document = connection.get();

            Elements parsingDivs = document.getElementsByAttributeValue("data-test", "historical-prices");
            Element tableEle = parsingDivs.get(0);

            Element tbody = tableEle.children().get(1);
            List<Dividend> dividends = new ArrayList<>();

            for (Element e : tbody.children()) {
                String txt = e.text();
                if (!txt.endsWith("Dividend")) {
                    continue;
                }
                String[] splits = txt.split(" ");
                int year = Integer.parseInt(splits[2]);
                int month = Months.numOfString(splits[0]);
                int day = Integer.parseInt(splits[1].replace(",", ""));
                String dividend = splits[3];

                if (month < 0) {
                    throw new RuntimeException(Months.numOfString(splits[0]) + "은 잘못된 Month 입니다.");
                }
                dividends.add(
                        Dividend.builder().date(LocalDateTime.of(year, month, day, 0, 0)).dividend(dividend).build());
            }
            scrapResult.setDividendEntities(dividends);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return scrapResult;
    }

    public Company scrapCompanyByTicker(String ticker) {
        String URL = String.format(SUMMARY_URL, ticker, ticker);
        try {

            Document document = Jsoup.connect(URL).get();
            Element titleEle = document.getElementsByTag("h1").get(0);
            String title = titleEle.text().split(" - ")[0].trim();
            return Company.builder().ticker(ticker).name(title).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
