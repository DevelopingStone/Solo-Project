package com.publicWifiSearch.domain.dto.openAPIRequestdtos;

import com.publicWifiSearch.domain.model.publicWifi.PublicWifiRecord;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicWifiSearch.domain.dto.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class OpenApiRequestPublicWifiRecordDto implements Dto<PublicWifiRecord> {

    @SerializedName("row")
    @Expose
    List<OpenApiRequestPublicWifiDto> publicWifiDto = new ArrayList<>();
    @Builder
    public OpenApiRequestPublicWifiRecordDto(List<OpenApiRequestPublicWifiDto> publicWifiDto) {
        this.publicWifiDto = publicWifiDto;
    }

    @Override
    public PublicWifiRecord toEntity() {
        return new PublicWifiRecord(publicWifiDto.stream().map(OpenApiRequestPublicWifiDto::toEntity).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return "JsonRequestPublicWifiRecordDto{" +
                "publicWifiDto=" + publicWifiDto +
                '}';
    }
}
