package com.dividend.exception.implementation.scrapper;

import com.dividend.exception.AbstractException;

import static com.dividend.exception.constant.HttpStatusCode.BAD_REQUEST;

public class TableNotFoundException extends AbstractException {
    private static final String DESCRIPTION = "요청하신 페이지에 배당금 정보가 없습니다.";

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getHttpStatusCode();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }

}
