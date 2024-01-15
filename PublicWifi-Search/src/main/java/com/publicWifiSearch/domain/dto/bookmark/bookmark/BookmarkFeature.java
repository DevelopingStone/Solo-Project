package com.publicWifiSearch.domain.dto.bookmark.bookmark;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public enum BookmarkFeature {

    ID("ID"),
    BOOKMARK_NAME("북마크 이름"),
    WIFI_NAME("와이파이명"),
    BOOKMARK_ENROLLMENT("등록일자"),
    REMARKS("비고");

    private final String features;
    public static final BookmarkFeature[] bookmarkFeatureName = BookmarkFeature.values();
    BookmarkFeature(String features){
        this.features = features;
    }

    public String getFeatureName() {
        return features;
    }

    public static String getFeatureNameForService(){
        Gson gson = new Gson();
        List<String> featureNames = new ArrayList<>();
        for (BookmarkFeature bookmarkFeature : bookmarkFeatureName) {
            featureNames.add(bookmarkFeature.getFeatureName());
        }
        return gson.toJson(featureNames);
    }
}
