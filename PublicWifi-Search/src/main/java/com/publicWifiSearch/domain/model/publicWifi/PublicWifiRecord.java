package com.publicWifiSearch.domain.model.publicWifi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicWifiSearch.domain.model.history.Coordinate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class PublicWifiRecord{
    @SerializedName("row")
    @Expose
    private List<PublicWifi> publicWifiData;
    private static final int NUMBER_OF_SEARCH_PUBLIC_WIFI=20;
    public PublicWifiRecord(List<PublicWifi> publicWifiData) {
        this.publicWifiData = publicWifiData;
    }

    public List<PublicWifi> getPublicWifiData(){
        return publicWifiData;
    }

    public void calcDistance(Coordinate coordinate){
        for (PublicWifi publicWifi: publicWifiData){
            publicWifi.calcDistance(coordinate);
        }
    }
    public List<PublicWifi> findNearestPublicWifi(){
        return publicWifiData.stream()
                .sorted(Comparator.comparing(PublicWifi::getDistance))
                .limit(NUMBER_OF_SEARCH_PUBLIC_WIFI)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "PublicWifiData{" +
                "publicWifiData=" + publicWifiData +
                '}';
    }
}

