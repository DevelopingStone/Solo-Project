package com.dividend.exception.implementation.member;

import com.dividend.exception.AbstractException;

import static com.dividend.exception.constant.HttpStatusCode.BAD_REQUEST;

public class AlreadyExistsUserException extends AbstractException {
    private static final String DESCRIPTION = "이미 존재하는 사용자명입니다.";

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getHttpStatusCode();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
