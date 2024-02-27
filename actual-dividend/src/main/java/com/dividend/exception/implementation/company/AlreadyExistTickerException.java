package com.dividend.exception.implementation.company;

import com.dividend.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class AlreadyExistTickerException extends AbstractException {
    private static final String DESCRIPTION = "이미 존재하는 ticker 입니다.";

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
