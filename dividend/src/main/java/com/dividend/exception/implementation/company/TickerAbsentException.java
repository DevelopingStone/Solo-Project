package com.dividend.exception.implementation.company;

import com.dividend.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class TickerAbsentException extends AbstractException {
    private static final String DESCRIPTION = "ticker가 없습니다.";

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
