package com.publicWifiSearch.domain.dto.bookmark.bookmark;

import com.publicWifiSearch.domain.model.bookMark.Bookmark;
import lombok.Builder;

public class BookmarkSaveDto {
    private final int bookMarkId;
    private final int bookmarkGroupId;
    private final String wifiName;
    private final String enrollmentDate;

    @Builder
    public BookmarkSaveDto(int bookMarkId, int bookmarkGroupId, String wifiName, String enrollmentDate) {
        this.bookMarkId = bookMarkId;
        this.bookmarkGroupId = bookmarkGroupId;
        this.wifiName = wifiName;
        this.enrollmentDate = enrollmentDate;
    }

    public Bookmark toEntity(){
        return new Bookmark(bookMarkId, bookmarkGroupId, wifiName, enrollmentDate);
    }
}
