package com.publicWifiSearch.domain.dto.history;

import com.google.gson.Gson;
import com.publicWifiSearch.domain.dto.constant.PublicWifiFeature;
import com.publicWifiSearch.domain.dto.constant.SearchHistoryFeature;

public class SearchHistoryResponseFeatureDto {
    public String featuresToJson(){
        Gson gson = new Gson();
        return gson.toJson(SearchHistoryFeature.getFeatureNameForService());
    }
}
