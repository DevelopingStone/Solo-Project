package com.publicWifiSearch.domain.dto.openAPI;

public enum UrlElement {

    HOMEPAGE("http://openapi.seoul.go.kr"),
    PORT(":8088/"),
    PUBLIC_KEY("616e584d52676f6d35365545766f53/"),
    DATA_TYPE("json/"),
    SERVICE_TYPE("TbPublicWifiInfo/"),
    FORMAT("UTF-8/"),
    SLASH("/");

    private final String element;

    UrlElement(String element){
        this.element = element;
    }

    @Override
    public String toString() {
        return this.element;
    }
}
