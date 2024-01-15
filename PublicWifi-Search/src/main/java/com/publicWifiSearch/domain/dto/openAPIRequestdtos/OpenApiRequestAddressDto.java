package com.publicWifiSearch.domain.dto.openAPIRequestdtos;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.google.gson.annotations.JsonAdapter;

import com.publicWifiSearch.domain.dto.Dto;
import com.publicWifiSearch.domain.dto.openAPI.jsonParser.AddressDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(AddressDeserializer.class)
public class OpenApiRequestAddressDto implements Dto<Address> {

    private String district;
    private String roadAddress;
    private String detailAddress;

    @Builder
    public OpenApiRequestAddressDto(String district, String roadAddress, String detailAddress) {
        this.district = district;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }

    @Override
    public String toString() {
        return "JsonRequestAddressDto{" +
                "district='" + district + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                '}';
    }

    @Override
    public Address toEntity() {
        return Address.builder()
                .district(district)
                .roadAddress(roadAddress)
                .detailAddress(detailAddress)
                .build();
    }
}