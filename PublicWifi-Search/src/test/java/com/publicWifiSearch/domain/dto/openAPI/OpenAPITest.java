package com.publicWifiSearch.domain.dto.openAPI;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenAPITest {

    @Test
    public void 올바르게_생성된_url은_주어진_url과_같다() throws MalformedURLException {
        // given
        int start = 1;
        int end = 1;
        String path = "http://openapi.seoul.go.kr:8088/616e584d52676f6d35365545766f53/json/TbPublicWifiInfo/1/1/";
        URL url = new URL(path);
        OpenAPI openAPI = new OpenAPI();
        URLMaker urlMaker = new URLMaker(start,end);
        // when
        openAPI.makeURL(urlMaker);

        // then
        assertEquals(openAPI.getUrl(), url);
    }

    @Test
    public void 올바르게_연결이_이루어진다면_IOException이_발생하지_않는다() throws IOException {
        // given
        OpenAPI openAPI = new OpenAPI();
        URLMaker urlMaker = new URLMaker(1,1);
        openAPI.makeURL(urlMaker);
        openAPI.makeHttpConnection();
        // then
        assertDoesNotThrow(openAPI::checkConnectionStatus);
    }

    @Test
    public void 올바른_연결후_올바르게_raw_json_문자열을_만들었다면_IOException이_발생하지_않는다() throws IOException{
        // given
        OpenAPI openAPI = new OpenAPI();
        URLMaker urlMaker = new URLMaker(1,1);
        openAPI.makeURL(urlMaker);
        openAPI.makeHttpConnection();
        // then
        assertDoesNotThrow(openAPI::checkConnectionStatus);
        assertDoesNotThrow(openAPI::convertToRawJson);
    }

    @Test
    public void raw_json_문자열을_올바르게_json으로_파싱한다면_IOException이_발생하지_않는다() throws IOException{
        // given
        OpenAPI openAPI = new OpenAPI();
        URLMaker urlMaker = new URLMaker(1,1);
        openAPI.makeURL(urlMaker);
        openAPI.makeHttpConnection();
        // then
        assertDoesNotThrow(openAPI::checkConnectionStatus);
        assertDoesNotThrow(openAPI::convertToRawJson);
        assertDoesNotThrow(openAPI::convertToJson);
    }

    @Test
    public void OpenAPI를_통해_가져온_총_데이터_수는_주어진_총_데이터_수와_같다(){
        int total = 23304;
        int expect = 0;
        OpenAPI openAPI = new OpenAPI();
        URLMaker urlMaker = new URLMaker(1,1);
        try {
            expect = openAPI.getNumberOfTotalData(urlMaker);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertEquals(expect, total);
    }

}