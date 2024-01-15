package com.publicWifiSearch.domain.model.bookMark;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Bookmark {
    private final int bookMarkId;
    private final int bookmarkGroupId;
    private final String bookmarkGroupName;
    private final String wifiName;
    private final String enrollmentDate;

    public Bookmark(int bookMarkId, String bookmarkGroupName, String wifiName, String enrollmentDate) {
        this.bookMarkId = bookMarkId;
        this.bookmarkGroupId = 0;
        this.bookmarkGroupName = bookmarkGroupName;
        this.wifiName = wifiName;
        this.enrollmentDate = enrollmentDate;
    }
    public Bookmark(int bookMarkId, int bookmarkGroupId, String wifiName, String enrollmentDate) {
        this.bookMarkId = bookMarkId;
        this.bookmarkGroupId = bookmarkGroupId;
        this.bookmarkGroupName = "";
        this.wifiName = wifiName;
        this.enrollmentDate = enrollmentDate;
    }
}
