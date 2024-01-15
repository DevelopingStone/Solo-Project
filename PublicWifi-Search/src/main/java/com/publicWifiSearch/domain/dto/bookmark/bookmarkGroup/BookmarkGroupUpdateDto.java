package com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup;

import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookmarkGroupUpdateDto {
    private final String bookmarkGroupId;
    private final String order;
    private final String bookmarkName;
    private final String enrollmentDate;
    private final String modifiedDate;

    @Builder
    public BookmarkGroupUpdateDto(String bookmarkGroupId, String order, String bookmarkName, String enrollmentDate, String modifiedDate) {
        this.bookmarkGroupId = bookmarkGroupId;
        this.order = order;
        this.bookmarkName = bookmarkName;
        this.enrollmentDate = enrollmentDate;
        this.modifiedDate = modifiedDate;
    }

    public BookmarkGroup toEntity(){
        return BookmarkGroup.builder()
                .bookmarkGroupId(Integer.parseInt(bookmarkGroupId))
                .order(Integer.parseInt(order))
                .bookmarkName(bookmarkName)
                .enrollmentDate(enrollmentDate)
                .modifiedDate(modifiedDate)
                .build();
    }

}
