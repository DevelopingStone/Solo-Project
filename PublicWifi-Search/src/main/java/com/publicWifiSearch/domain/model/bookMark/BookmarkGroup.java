package com.publicWifiSearch.domain.model.bookMark;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookmarkGroup {

    private final int bookmarkGroupId;
    private final int order;
    private final String bookmarkName;
    private final String enrollmentDate;
    private final String modifiedDate;
    @Builder
    public BookmarkGroup(int bookmarkGroupId, int order, String bookmarkName, String enrollmentDate, String modifiedDate) {
        this.bookmarkGroupId = bookmarkGroupId;
        this.order = order;
        this.bookmarkName = bookmarkName;
        this.enrollmentDate = enrollmentDate;
        this.modifiedDate = modifiedDate;
    }
}
