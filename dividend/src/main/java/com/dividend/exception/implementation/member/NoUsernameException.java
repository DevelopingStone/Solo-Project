package com.dividend.exception.implementation.member;

import com.dividend.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NoUsernameException extends AbstractException {
    private static final String DESCRIPTION = "존재하지 않는 ID 입니다.";

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
