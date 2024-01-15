package com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;

import java.util.List;

public class BookmarkGroupRecordDto {
    private List<BookmarkGroup> bookmarkGroupBundle;

    public BookmarkGroupRecordDto(List<BookmarkGroup> bookmarkGroupBundle) {
        this.bookmarkGroupBundle = bookmarkGroupBundle;
    }

    private List<String> collectProperty(BookmarkGroup bookmarkGroup){
        String id = String.valueOf(bookmarkGroup.getBookmarkGroupId());
        String order= String.valueOf(bookmarkGroup.getOrder());
        String bookmarkGroupName = bookmarkGroup.getBookmarkName();
        String bookmarkGroupEnrollment = bookmarkGroup.getEnrollmentDate();
        String bookmarkGroupModified = bookmarkGroup.getModifiedDate();
        return List.of(id, bookmarkGroupName, order, bookmarkGroupEnrollment,bookmarkGroupModified ,"");
    }

    public String toJson() {
        JsonArray bookmarkGroupJsonBundle = new JsonArray();
        for (BookmarkGroup bookmarkGroup: bookmarkGroupBundle){
            List<String> bookmarkGroupProperty = collectProperty(bookmarkGroup);
            BookmarkGroupFeature[] bookmarkGroupFeatures = BookmarkGroupFeature.bookmarkGroupFeatureName;
            JsonObject property = new JsonObject();
            for (int i = 0; i < bookmarkGroupFeatures.length; i++) {

                property.addProperty(bookmarkGroupFeatures[i].getFeatureName(),bookmarkGroupProperty.get(i));
            }
            bookmarkGroupJsonBundle.add(property);
        }
        return bookmarkGroupJsonBundle.toString();
    }

}
