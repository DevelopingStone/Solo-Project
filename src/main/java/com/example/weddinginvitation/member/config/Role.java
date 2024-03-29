package com.example.weddinginvitation.member.config;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("admin"),
    MEMBER("member");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
