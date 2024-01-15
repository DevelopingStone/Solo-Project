package com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Address implements PublicWifiDetail {

    private String district;
    private String roadAddress;
    private String detailAddress;

    @Builder
    public Address(String district, String roadAddress, String detailAddress) {
        this.district = district;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "district='" + district + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                '}';
    }

    @Override
    public List<String> toStringAllProperty() {
        return List.of(this.district, this.roadAddress, this.detailAddress);
    }
}
