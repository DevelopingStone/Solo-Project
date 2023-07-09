package com.dividend.service.module;

import com.dividend.model.domain.Company;
import com.dividend.model.vo.ScrapedResult;

public interface Scrapper {

    ScrapedResult scrapDividendByCompany(Company company);

    Company scrapCompanyByTicker(String ticker);

}
