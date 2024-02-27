package com.dividend.exception.implementation.company;

import com.dividend.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class FailScrapCompanyException extends AbstractException {
    private static final String DESCRIPTION = "failed to scrap ticker -> ";
    private final String ticker;

    public FailScrapCompanyException(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION+this.ticker;
    }
}
