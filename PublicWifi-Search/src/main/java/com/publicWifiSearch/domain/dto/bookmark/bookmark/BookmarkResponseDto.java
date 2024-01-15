package com.publicWifiSearch.domain.dto.bookmark.bookmark;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.publicWifiSearch.domain.dto.constant.PublicWifiFeature;
import com.publicWifiSearch.domain.model.bookMark.Bookmark;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BookmarkResponseDto {

    private List<Bookmark> bookmarkBundle;

    public BookmarkResponseDto(List<Bookmark> bookmarkBundle) {
        this.bookmarkBundle = bookmarkBundle;
    }

    private List<String> collectProperty(Bookmark bookmark){
        String bookmarkID = String.valueOf(bookmark.getBookMarkId());
        return List.of(bookmarkID, bookmark.getBookmarkGroupName(), bookmark.getWifiName(), bookmark.getEnrollmentDate(), "");
    }

    public String toJson(){
        JsonArray bookmarkJsonArray = new JsonArray();
        for (Bookmark bookmark : bookmarkBundle) {
            List<String> bookmarkProperty = collectProperty(bookmark);
            BookmarkFeature[] features = BookmarkFeature.bookmarkFeatureName;
            JsonObject property = new JsonObject();
            for (int i = 0; i < features.length; i++) {

                property.addProperty(features[i].getFeatureName(),bookmarkProperty.get(i));
            }
            bookmarkJsonArray.add(property);
        }

        return bookmarkJsonArray.toString();


    }
}
