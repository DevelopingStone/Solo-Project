package com.publicWifiSearch.service;

import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestPublicWifiRecordDto;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifiRecord;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class OpenAPIServiceTest {

    @DisplayName("공공 데이터 가져오는 테스트")
    @Test
    public void 데이터_가져오는_것을_성공하면_오류가_발생하지_않는다(){
        // given
        OpenAPIService openAPIService = new OpenAPIService();

        // then
        assertDoesNotThrow(openAPIService::requestToOpenAPI);
    }

    @Test
    public void test(){
        // given
        OpenAPIService openAPIService = new OpenAPIService();

        List<OpenApiRequestPublicWifiRecordDto> publicWifiDto = openAPIService.requestToOpenAPI();

        List<PublicWifi> publicWifiList = new ArrayList<>();

        for (OpenApiRequestPublicWifiRecordDto openApiRequestPublicWifiRecordDto : publicWifiDto){
            PublicWifiRecord publicWifiRecord = openApiRequestPublicWifiRecordDto.toEntity();
            publicWifiList.addAll(publicWifiRecord.getPublicWifiData());
        }
        List<Address> addresses = new ArrayList<>();
        List<Installation> installations = new ArrayList<>();
        List<Wifi> wifiList = new ArrayList<>();
        List<String> id = new ArrayList<>();

        for (PublicWifi publicWifi: publicWifiList){
            id.add(publicWifi.getManagementId());
        }

        for (PublicWifi publicWifi: publicWifiList){
            addresses.add((publicWifi.getAddress()));
        }

        for (PublicWifi publicWifi: publicWifiList){
            installations.add(publicWifi.getInstallation());
        }

        for (PublicWifi publicWifi: publicWifiList){
            wifiList.add(publicWifi.getWifi());
        }

    }


}