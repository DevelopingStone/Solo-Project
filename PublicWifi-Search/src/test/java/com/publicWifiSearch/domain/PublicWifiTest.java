package com.publicWifiSearch.domain;

import com.google.gson.*;
import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestPublicWifiRecordDto;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PublicWifiTest {
    static String baseUrl = "http://openapi.seoul.go.kr:8088/";
    static String publicKey = "616e584d52676f6d35365545766f53/";
    static String dataType = "json/";
    static String serviceType = "TbPublicWifiInfo/";

    static String makeURL(int startIndex, int endIndex){
        String start = startIndex + "/";
        String end = endIndex + "/";

        String path = baseUrl +
                publicKey +
                dataType +
                serviceType +
                start +
                end;

        return path;
    }

    static HttpURLConnection makeConnection(String url) throws IOException {
        URL openApiUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) openApiUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        return conn;
    }

    static String makeJSON(HttpURLConnection connection) throws IOException {
        StringBuilder json = new StringBuilder();
        System.out.println(connection.getInputStream().toString());
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonContent="";
            while ((jsonContent=bufferedReader.readLine())!=null){
                json.append(jsonContent);
            }

        }catch (IOException ioException){
            System.out.println("입출력 오류입니다.");
        }
        return json.toString();
    }

    static String getResult(String json) throws IOException{
        String result="";

        try{
            JsonObject openApiData = (JsonObject) JsonParser.parseString(json);
            JsonObject tbPublicWifiData;
            if(openApiData.has("TbPublicWifiInfo")){
                tbPublicWifiData = openApiData.get("TbPublicWifiInfo").getAsJsonObject();
                result = tbPublicWifiData.get("RESULT").toString();
            }
            result = openApiData.get("RESULT").toString();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    static int getNumOfTotalData(){

        int numOfTotalData=0;
        try{
            String path = makeURL(1, 1);
            HttpURLConnection conn = PublicWifiTest.makeConnection(path);
            String json = makeJSON(conn);
            JsonObject openApiData = (JsonObject) JsonParser.parseString(json);
            JsonObject tbPublicWifiData;
            if(openApiData.has("TbPublicWifiInfo")){
                tbPublicWifiData = openApiData.get("TbPublicWifiInfo").getAsJsonObject();
                numOfTotalData = tbPublicWifiData.get("list_total_count").getAsInt();
            }
            else {
                numOfTotalData=-1;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return numOfTotalData;
    }

    @DisplayName("데이터의 범위가 올바른 경우 테스트")
    @Test
    public void 올바른_데이터_범위를_입력하면_true를_리턴한다() throws IOException {
        String path = makeURL(1, 1000);
        HttpURLConnection conn = makeConnection(path);
        String json = makeJSON(conn);
        String result = getResult(json);
        assertTrue(result.contains("INFO-000"));
    }

    @DisplayName("요청한 데이터의 수가 1000개를 초과하면 에러가 발생하는지 확인하는 테스트")
    @Test
    public void 요청한_데이터의_수가_1000개를_초과하면_ERROR_CODE_336을_리턴한다() throws IOException{
        String path = makeURL(23000, 24000);
        HttpURLConnection conn = makeConnection(path);
        String json = makeJSON(conn);
        String result = getResult(json);
        assertTrue(result.contains("ERROR-336"));
    }

    @DisplayName("요청한 데이터의 수가 1000개 이하이고 서버에 저장된 데이터의 수보다 큰 인덱스를 요청했을 때 정상 메세지를 보내는지 확인하는 테스트")
    @Test
    public void 요청_데이터의_수는_1000개_미만이고_총_데이터_수보다_큰_인덱스를_요청_시_정상_종료_코드를_리턴한다() throws IOException{
        String path = makeURL(23000, 23339);
        HttpURLConnection conn = makeConnection(path);
        String json = makeJSON(conn);
        String result = getResult(json);
        assertTrue(result.contains("INFO-000"));
    }
    @DisplayName("데이터의 수 구하기 테스트")
    @Test
    public void getTotalDataCountTest() throws IOException {

        int numOfData = getNumOfTotalData();
        assertEquals(numOfData, 23304);
    }

    @DisplayName("publicWifiData 객체 직렬화")
    @Test
    public void gson을_이용한_publicWifiData_객체_직렬화_테스트() throws IOException {
        String url = makeURL(1, 1000);
        HttpURLConnection conn = makeConnection(url);
        String json = makeJSON(conn);
        Gson gson = new Gson();

        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        JsonObject tbWifi = (JsonObject) jsonObject.get("TbPublicWifiInfo");
        OpenApiRequestPublicWifiRecordDto publicWifiSaveDto = gson.fromJson(tbWifi, OpenApiRequestPublicWifiRecordDto.class);
        System.out.println(publicWifiSaveDto);

    }

    @DisplayName("모든 데이터 json 포맷으로 가져오기 테스트")
    @Test
    public void 모든_데이터를_가져왔을때_총_데이터_수와_동일하다() throws IOException {
        int startIndex = 1;
        int totalData = 0;
        List<PublicWifi> publicWifiList = new ArrayList<>();
        int transmissionUnit=1000;
        int numOfTotalData = getNumOfTotalData();
        String normalStatusCode = "INFO-000";
        String statusCode;
        while (true){
            String url = makeURL(startIndex, startIndex+transmissionUnit);
            HttpURLConnection connection = makeConnection(url);
            String json = makeJSON(connection);
            statusCode = getResult(json);
            if(!statusCode.equals(normalStatusCode)){
                break;
            }

        }


    }

}
