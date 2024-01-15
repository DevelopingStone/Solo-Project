package com.publicWifiSearch.domain.dto.constant;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public enum SearchHistoryFeature {

    ID("ID"),
    COORDINATE_X("X좌표"),
    COORDINATE_Y("Y좌표"),
    SEARCH_DATE("조회일자"),
    REMARKS("비고");
    public static final SearchHistoryFeature[] searchHistoryFeatures = SearchHistoryFeature.values();
    private final String featureName;

    SearchHistoryFeature(String featureName){
        this.featureName = featureName;
    }

    public String getFeatureName() {
        return featureName;
    }
    public static String getFeatureNameForService(){
        Gson gson = new Gson();
        List<String> featureNames = new ArrayList<>();
        for (SearchHistoryFeature searchHistoryFeature : searchHistoryFeatures) {
            featureNames.add(searchHistoryFeature.getFeatureName());
        }
        return gson.toJson(featureNames);
    }
}
