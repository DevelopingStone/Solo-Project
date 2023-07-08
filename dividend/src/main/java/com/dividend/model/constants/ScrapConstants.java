package com.dividend.model.constants;

public final class ScrapConstants {
    public static final String REQUEST_URI = "https://finance.yahoo.com/quote/%s/history?period1=%d&period2=%d&interval=1mo";

    public static final int ONE_THOUSAND_MILLISECOND=1000;
    public static final int FIRST_TABLE=0;

    public static final int TBODY=1;
    public static final int MONTH_POSITION =0;
    public static final int DAY_POSITION =1;
    public static final int YEAR_POSITION =2;
    public static final int DIVIDEND_POSITION =3;
    public static final int EMPTY_HOUR=0;
    public static final int EMPTY_MINUTE=0;
    public static final int START_TIME = 86400;
    public static final int JANUARY=0;

    public static final String BLANK = " ";
    public static final String COMMA = ",";
    public static final String WHITE_SPACE="";
    public static final String DIVIDEND = "Dividend";
    public static final String DIVIDEND_TABLE_ATTRIBUTE_NAME = "data-test";
    public static final String DIVIDEND_TABLE_ATTRIBUTE_VALUE = "historical-prices";

    private ScrapConstants(){}
}
