package com.dividend;

import com.dividend.config.Months;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DividendApplication {


    public static void main(String[] args) {

        SpringApplication.run(DividendApplication.class, args);

        final String URL = "https://finance.yahoo.com/quote/COKE/history?period1=99100800&period2=1707091200&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true";
        try {
            Connection connection = Jsoup.connect(URL);
            Document document = connection.get();

            Elements elements = document.getElementsByAttributeValue("data-test", "historical-prices");
            Element element = elements.get(0);

            Element tbody = element.children().get(1);
            for (Element e : tbody.children()) {
                String txt = e.text();
                if (!txt.endsWith("DividendRepository")) {
                    continue;
                }
                String[] splits = txt.split(" ");
                int year = Integer.parseInt(splits[2]);
                int month = Months.numOfString(splits[0]);

                int day = Integer.parseInt(splits[1].replace(",", ""));
                double dividend = Double.parseDouble(splits[3]);

                System.out.printf("%d년 %d월 %d일 배당금 : %.2f %n", year, month, day, dividend);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
