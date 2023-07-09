package com.dividend.exception.handler;

import com.dividend.exception.AbstractException;
import com.dividend.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AbstractException.class)
    protected ResponseEntity<?> handleCustomException(AbstractException abstractException) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(abstractException.getStatusCode())
                .message(abstractException.getMessage())
                .build();

        return new ResponseEntity<>(
                errorResponse,
                Objects.requireNonNull(
                        HttpStatus.resolve(abstractException.getStatusCode()))
        );
    }
}
