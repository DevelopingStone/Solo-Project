package com.dividend.exception.implementation.company;

import com.dividend.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NoCompanyException extends AbstractException {
    private static final String DESCRIPTION = "존재하지 않는 회사명입니다.";

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
