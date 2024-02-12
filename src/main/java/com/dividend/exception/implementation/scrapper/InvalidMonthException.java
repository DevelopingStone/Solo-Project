package com.dividend.exception.implementation.scrapper;

import com.dividend.exception.AbstractException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.dividend.exception.constant.HttpStatusCode.BAD_REQUEST;

@AllArgsConstructor
@Getter
public class InvalidMonthException extends AbstractException {

    private static final String DESCRIPTION = "요청하신 페이지의 날짜 정보 중 월에 해당하는 정보가 유효하지 않습니다." +
            "\n월에 해당하는 값이 1미만 12초과입니다.";

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getHttpStatusCode();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
