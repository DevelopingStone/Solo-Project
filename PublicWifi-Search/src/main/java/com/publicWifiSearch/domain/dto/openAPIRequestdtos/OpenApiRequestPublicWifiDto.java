package com.publicWifiSearch.domain.dto.openAPIRequestdtos;

import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.google.gson.annotations.JsonAdapter;
import com.publicWifiSearch.domain.dto.Dto;
import com.publicWifiSearch.domain.dto.openAPI.jsonParser.PublicWifiDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(PublicWifiDeserializer.class)
public class OpenApiRequestPublicWifiDto implements Dto<PublicWifi> {
    private String managementId;

    private OpenApiRequestAddressDto addressDto;

    private OpenApiRequestInstallationDto installationDto;

    private OpenApiRequestWifiDto wifiDto;
    @Builder
    public OpenApiRequestPublicWifiDto(String managementId, OpenApiRequestAddressDto address, OpenApiRequestInstallationDto installation, OpenApiRequestWifiDto wifi) {
        this.addressDto = address;
        this.installationDto = installation;
        this.wifiDto = wifi;
        this.managementId = managementId;
    }


    @Override
    public PublicWifi toEntity() {
        return PublicWifi.builder()
                .managementId(managementId)
                .address(addressDto.toEntity())
                .installation(installationDto.toEntity())
                .wifi(wifiDto.toEntity())
                .build();
    }

    @Override
    public String toString() {
        return "JsonRequestPublicWifiDto{" +
                "managementId='" + managementId + '\'' +
                ", addressDto=" + addressDto +
                ", installationDto=" + installationDto +
                ", wifiDto=" + wifiDto +
                '}';
    }
}
