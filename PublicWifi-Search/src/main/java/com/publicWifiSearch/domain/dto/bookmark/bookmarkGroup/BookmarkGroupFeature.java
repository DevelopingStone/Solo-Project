package com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public enum BookmarkGroupFeature {
    ID("ID"),
    BOOKMARK_NAME("북마크 이름"),
    ORDER("순서"),
    BOOKMARK_ENROLLMENT("등록일자"),
    BOOKMARK_MODIFIED("수정일자"),
    REMARKS("비고");
    public static final BookmarkGroupFeature[] bookmarkGroupFeatureName = BookmarkGroupFeature.values();
    private final String features;

    BookmarkGroupFeature(String features){
        this.features = features;
    }

    public String getFeatureName() {
        return features;
    }

    public static String getFeatureNameForService(){
        Gson gson = new Gson();
        List<String> featureNames = new ArrayList<>();
        for (BookmarkGroupFeature bookmarkGroupFeature : bookmarkGroupFeatureName) {
            featureNames.add(bookmarkGroupFeature.getFeatureName());
        }
        return gson.toJson(featureNames);
    }
}
