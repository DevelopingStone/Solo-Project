package com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;

import java.util.List;

public class BookmarkGroupNameResponseDto {
    private List<BookmarkGroup> bookmarkGroupBundle;

    public BookmarkGroupNameResponseDto(List<BookmarkGroup> bookmarkGroupNameBundle) {
        this.bookmarkGroupBundle = bookmarkGroupNameBundle;
    }

    public String toJson() {
        JsonArray bookmarkGroupNameJsonBundle = new JsonArray();
        for (BookmarkGroup bookmarkGroup: bookmarkGroupBundle){

            JsonObject property = new JsonObject();
            property.addProperty(BookmarkGroupFeature.BOOKMARK_NAME.toString(),bookmarkGroup.getBookmarkName());
            property.addProperty(BookmarkGroupFeature.ID.toString(), String.valueOf(bookmarkGroup.getBookmarkGroupId()));
            bookmarkGroupNameJsonBundle.add(property);
        }
        System.out.println(bookmarkGroupNameJsonBundle);
        return bookmarkGroupNameJsonBundle.toString();
    }

}
