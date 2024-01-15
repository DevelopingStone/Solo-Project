package com.publicWifiSearch.domain.dto.publicWifi;

import com.google.gson.Gson;
import com.publicWifiSearch.domain.dto.constant.PublicWifiFeature;

public class PublicWifiFeatureDto {

    public String featuresToJson(){
        Gson gson = new Gson();
        return gson.toJson(PublicWifiFeature.getFeatureNameForService());
    }
}
