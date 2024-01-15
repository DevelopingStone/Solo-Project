package com.publicWifiSearch.domain.dto.openAPIRequestdtos;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import com.publicWifiSearch.domain.dto.Dto;
import com.google.gson.annotations.JsonAdapter;

import com.publicWifiSearch.domain.dto.openAPI.jsonParser.WifiDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(WifiDeserializer.class)
public class OpenApiRequestWifiDto implements Dto<Wifi> {

    private String wifiName;
    private double coordinateX;
    private double coordinateY;
    private String connectionEnvironment;
    private String networkType;
    private String service;
    private String dateOfWork;
    @Builder
    public OpenApiRequestWifiDto(String wifiName, double coordinateX, double coordinateY, String connectionEnvironment, String networkType, String service, String dateOfWork) {
        this.wifiName = wifiName;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.connectionEnvironment = connectionEnvironment;
        this.networkType = networkType;
        this.service = service;
        this.dateOfWork = dateOfWork;
    }

    @Override
    public String toString() {
        return "JsonRequestWifiDto{" +
                "wifiName='" + wifiName + '\'' +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", connectionEnvironment='" + connectionEnvironment + '\'' +
                ", networkType='" + networkType + '\'' +
                ", service='" + service + '\'' +
                ", dateOfWork='" + dateOfWork + '\'' +
                '}';
    }

    @Override
    public Wifi toEntity() {
        return Wifi.builder()
                .wifiName(wifiName)
                .coordinateX(coordinateX)
                .coordinateY(coordinateY)
                .connectionEnvironment(connectionEnvironment)
                .networkType(networkType)
                .service(service)
                .dateOfWork(dateOfWork)
                .build();
    }
}

