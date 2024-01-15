package com.publicWifiSearch.domain.model.publicWifi;

import com.publicWifiSearch.domain.model.history.Coordinate;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor

public class PublicWifi {

    private String managementId;

    private Address address;

    private Installation installation;

    private Wifi wifi;
    private Double distance=0.0;
    @Builder
    public PublicWifi(String managementId, Address address, Installation installation, Wifi wifi) {
        this.address = address;
        this.installation = installation;
        this.wifi = wifi;
        this.managementId = managementId;
    }

    public void calcDistance(Coordinate coordinate){
        distance = wifi.calculateDistance(coordinate);
    }

    @Override
    public String toString() {
        return "PublicWifi{" +
                "managementId='" + managementId + '\'' +
                ", address=" + address +
                ", installation=" + installation +
                ", wifi=" + wifi +
                '}';
    }
}
