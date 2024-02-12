package com.dividend.exception.implementation.scrapper;

import com.dividend.exception.AbstractException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.dividend.exception.constant.HttpStatusCode.BAD_REQUEST;

@AllArgsConstructor
@Getter
public class NoH1TagExistenceException extends AbstractException {
    private static final String DESCRIPTION = "h1 태그가 없습니다.";

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getHttpStatusCode();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
