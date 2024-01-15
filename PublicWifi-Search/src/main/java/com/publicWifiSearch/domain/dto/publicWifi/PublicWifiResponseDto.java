package com.publicWifiSearch.domain.dto.publicWifi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.publicWifiSearch.domain.dto.constant.PublicWifiFeature;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PublicWifiResponseDto {

    List<PublicWifi> publicWifiBundle;
    @Builder
    public PublicWifiResponseDto(List<PublicWifi> publicWifiBundle) {
        this.publicWifiBundle = publicWifiBundle;
    }

    public List<String> collectProperty(PublicWifi publicWifi) {
        Address address = publicWifi.getAddress();
        Installation installation = publicWifi.getInstallation();
        Wifi wifi = publicWifi.getWifi();

        String distance = String.format("%.2f", publicWifi.getDistance());
        String coordinateX = String.format("%.2f", publicWifi.getWifi().getCoordinateX());
        String coordinateY = String.format("%.2f", publicWifi.getWifi().getCoordinateY());

        return List.of(distance, publicWifi.getManagementId(), address.getDistrict(), wifi.getWifiName(),
                address.getRoadAddress(), address.getDetailAddress(), installation.getInstallLocation(), installation.getInstallType(),
                installation.getInstallOffice(), wifi.getService(), wifi.getNetworkType(), installation.getInstallYear(),
                installation.getInstallDivision(), wifi.getConnectionEnvironment(), coordinateX, coordinateY, wifi.getDateOfWork());
    }

    public String NearestPublicWifiToJson() {
        JsonArray publicWifiJsonArray = new JsonArray();
        for (PublicWifi publicWifi : publicWifiBundle) {
            List<String> publicWifiProperty = collectProperty(publicWifi);
            PublicWifiFeature[] features = PublicWifiFeature.publicWifiFeatures;
            JsonObject property = new JsonObject();
            for (int i = 0; i < features.length; i++) {

                property.addProperty(features[i].getKoreanName(),publicWifiProperty.get(i));
            }
            publicWifiJsonArray.add(property);
        }

        return publicWifiJsonArray.toString();
    }

}
