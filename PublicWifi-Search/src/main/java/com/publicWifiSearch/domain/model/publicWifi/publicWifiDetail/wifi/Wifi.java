package com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi;

import com.publicWifiSearch.domain.model.history.Coordinate;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Wifi implements PublicWifiDetail {

    private String wifiName;
    private double coordinateY;
    private double coordinateX;
    private String connectionEnvironment;
    private String networkType;
    private String service;
    private String dateOfWork;
    @Builder
    public Wifi(String wifiName, double coordinateX, double coordinateY, String connectionEnvironment, String networkType, String service, String dateOfWork) {
        this.wifiName = wifiName;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.connectionEnvironment = connectionEnvironment;
        this.networkType = networkType;
        this.service = service;
        this.dateOfWork = dateOfWork;
    }

    public double calculateDistance(Coordinate coordinate){
        double differenceX = Math.pow(Math.abs(coordinate.getX()-this.coordinateX), 2);
        double differenceY = Math.pow(Math.abs(coordinate.getY() - this.coordinateY), 2);
        return Math.sqrt(differenceX + differenceY);
    }

    @Override
    public String toString() {
        return "Wifi{" +
                "wifiName='" + wifiName + '\'' +
                ", coordinateX='" + coordinateX + '\'' +
                ", coordinateY='" + coordinateY + '\'' +
                ", connectionEnvironment='" + connectionEnvironment + '\'' +
                ", networkType='" + networkType + '\'' +
                ", service='" + service + '\'' +
                ", dateOfWork='" + dateOfWork + '\'' +
                '}';
    }

    @Override
    public List<String> toStringAllProperty() {
        String coordinateX = String.format("%.2f", this.coordinateX);
        String coordinateY = String.format("%.2f", this.coordinateY);

        return List.of(this.wifiName, coordinateX, coordinateY,this.connectionEnvironment, this.networkType, this.service, this.dateOfWork);
    }
}
