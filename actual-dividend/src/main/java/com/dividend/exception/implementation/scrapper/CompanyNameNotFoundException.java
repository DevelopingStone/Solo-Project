package com.dividend.exception.implementation.scrapper;

import com.dividend.exception.AbstractException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.dividend.exception.constant.HttpStatusCode.BAD_REQUEST;

@AllArgsConstructor
@Getter
public class CompanyNameNotFoundException extends AbstractException {
    private static final String DESCRIPTION = "일치하는 회사 이름이 없습니다.";

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getHttpStatusCode();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
