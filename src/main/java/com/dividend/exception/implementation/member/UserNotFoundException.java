package com.dividend.exception.implementation.member;

import com.dividend.exception.AbstractException;

import static com.dividend.exception.constant.HttpStatusCode.BAD_REQUEST;

public class UserNotFoundException extends AbstractException {

    private static final String DESCRIPTION = "couldn't find user -> ";
    private final String username;
    public UserNotFoundException(String username){
        this.username = username;
    }

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getHttpStatusCode();
    }

    @Override
    public String getMessage() {
        return DESCRIPTION.concat(username);
    }
}
