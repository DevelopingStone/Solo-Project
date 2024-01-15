package com.publicWifiSearch.domain.dto.openAPIRequestdtos;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.google.gson.annotations.JsonAdapter;

import com.publicWifiSearch.domain.dto.Dto;
import com.publicWifiSearch.domain.dto.openAPI.jsonParser.InstallationDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(InstallationDeserializer.class)
public class OpenApiRequestInstallationDto implements Dto<Installation> {

    private String installLocation;
    private String installType;
    private String installOffice;
    private String installYear;
    private String installDivision;

    @Builder
    public OpenApiRequestInstallationDto(String installLocation, String installType, String installOffice, String installYear, String installDivision) {
        this.installLocation = installLocation;
        this.installType = installType;
        this.installOffice = installOffice;
        this.installYear = installYear;
        this.installDivision = installDivision;
    }

    @Override
    public String toString() {
        return "Installation{" +
                "installLocation='" + installLocation + '\'' +
                ", installType='" + installType + '\'' +
                ", installOffice='" + installOffice + '\'' +
                ", installYear='" + installYear + '\'' +
                ", installDivision='" + installDivision + '\'' +
                '}';
    }

    @Override
    public Installation toEntity() {
        return Installation.builder()
                .installLocation(installLocation)
                .installType(installType)
                .installOffice(installOffice)
                .installYear(installYear)
                .installDivision(installDivision)
                .build();
    }
}
