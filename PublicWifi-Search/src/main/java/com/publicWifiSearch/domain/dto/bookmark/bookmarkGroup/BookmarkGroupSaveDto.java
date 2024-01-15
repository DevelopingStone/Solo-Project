package com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup;

import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookmarkGroupSaveDto {
    private final int bookmarkGroupId;
    private final int order;
    private final String bookmarkName;
    private final String enrollmentDate;
    private final String modifiedDate;
    @Builder
    public BookmarkGroupSaveDto(int bookmarkGroupId, int order, String bookmarkName, String enrollmentDate, String modifiedDate) {
        this.bookmarkGroupId = bookmarkGroupId;
        this.order = order;
        this.bookmarkName = bookmarkName;
        this.enrollmentDate = enrollmentDate;
        this.modifiedDate = modifiedDate;
    }

    public BookmarkGroup toEntity(){
        return BookmarkGroup.builder()
                .bookmarkGroupId(bookmarkGroupId)
                .order(order)
                .bookmarkName(bookmarkName)
                .enrollmentDate(enrollmentDate)
                .modifiedDate(modifiedDate)
                .build();
    }

}
