package com.dividend.scrapper;

import com.dividend.model.Company;
import com.dividend.model.ScrapedResult;

public interface Scrapper {

    ScrapedResult scrap(Company company);
    Company scrapCompanyByTicker(String ticker);

}
