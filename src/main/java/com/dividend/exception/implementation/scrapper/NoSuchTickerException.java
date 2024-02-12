package com.dividend.exception.implementation.scrapper;

import com.dividend.exception.AbstractException;

import static com.dividend.exception.constant.HttpStatusCode.BAD_REQUEST;

public class NoSuchTickerException extends AbstractException {
    private static final String DESCRIPTION = "요청하신 ticker에 해당하는 회사를 찾을 수 없습니다.";

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getHttpStatusCode();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
