package com.publicWifiSearch.service;

import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestPublicWifiRecordDto;
import com.publicWifiSearch.domain.dto.openAPI.URLMaker;
import com.publicWifiSearch.domain.dto.openAPI.OpenAPI;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OpenAPIService {
    private static final int TRANSMISSION_UNIT = 1000;
    public OpenAPIService(){

    }

    public int getNumberOfTotalData(){
        URLMaker urlMaker = new URLMaker(1, 1);
        OpenAPI openAPI = new OpenAPI();
        return openAPI.getNumberOfTotalData(urlMaker);
    }

    public OpenApiRequestPublicWifiRecordDto getPublicWifiData(int start, int end){
        URLMaker urlMaker = new URLMaker(start, end);
        OpenAPI openAPI = new OpenAPI();
        return openAPI.request(urlMaker);
    }

    public List<OpenApiRequestPublicWifiRecordDto> requestToOpenAPI(){
        int numberOfTotalData = getNumberOfTotalData();

        int numberOfRequest = numberOfTotalData / TRANSMISSION_UNIT;
        if(numberOfTotalData % TRANSMISSION_UNIT!=0){
            numberOfRequest = numberOfTotalData / TRANSMISSION_UNIT + 1;
        }

        List<OpenApiRequestPublicWifiRecordDto> openApiRequestPublicWifiRecordDtoList = new ArrayList<>();
        try{
            for (int i = 0; i < numberOfRequest; i++) {
                OpenApiRequestPublicWifiRecordDto openApiRequestPublicWifiRecordDto = getPublicWifiData(1+TRANSMISSION_UNIT*i, TRANSMISSION_UNIT*(i+1));
                openApiRequestPublicWifiRecordDtoList.add(openApiRequestPublicWifiRecordDto);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return openApiRequestPublicWifiRecordDtoList;
    }


}
