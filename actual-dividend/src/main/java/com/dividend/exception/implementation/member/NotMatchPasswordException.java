package com.dividend.exception.implementation.member;

import com.dividend.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NotMatchPasswordException extends AbstractException {
    private static final String DESCRIPTION = "비밀번호가 일치하지 않습니다";

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
