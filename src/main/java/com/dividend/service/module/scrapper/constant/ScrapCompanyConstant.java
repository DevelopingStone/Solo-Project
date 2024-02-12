package com.dividend.service.module.scrapper.constant;

public final class ScrapCompanyConstant {
    public static final String SUMMARY_URI = "https://finance.yahoo.com/quote/%s?p=%s";

    public static final int FIRST_H1_TAG_POSITION = 0;
    public static final int FIRST_COMPANY_NAME_POSITION = 0;
    public static final String H1_TAG = "h1";
    public static final String OPEN_BRACKET = "\\(";

    private ScrapCompanyConstant() {
    }
}
