package com.publicWifiSearch.service;

import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestPublicWifiRecordDto;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PublicWifiServiceTest {
    private OpenAPIService openAPIService;
    private DbConnectionMaker dbConnectionMaker;
    private PublicWifiService publicWifiService;
    private List<OpenApiRequestPublicWifiRecordDto> requestPublicWifiRecordDto;

    @BeforeEach
    public void deleteAll(){
        openAPIService = new OpenAPIService();
        requestPublicWifiRecordDto = openAPIService.requestToOpenAPI();
        dbConnectionMaker = new SqliteConnectionMaker();
        publicWifiService = new PublicWifiService(dbConnectionMaker);
        publicWifiService.deleteOpenApiRequest();
    }
    @AfterEach
    public void deleteAfter(){
        publicWifiService.deleteOpenApiRequest();
    }
    @Test
    public void insertTest(){
        assertDoesNotThrow(()->publicWifiService.saveOpenApiRequest(requestPublicWifiRecordDto));
    }
}