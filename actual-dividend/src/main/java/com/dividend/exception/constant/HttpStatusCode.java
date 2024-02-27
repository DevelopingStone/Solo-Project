package com.dividend.exception.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum HttpStatusCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value());

    private final int httpStatusCode;

}
