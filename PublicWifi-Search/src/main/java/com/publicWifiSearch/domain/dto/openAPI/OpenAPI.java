package com.publicWifiSearch.domain.dto.openAPI;

import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestPublicWifiRecordDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class OpenAPI {

    private static final Object CONTENT_EMPTY = null;
    private static final String FORMAT = "UTF-8";
    private static final String NUMBER_OF_TOTAL_DATA = "list_total_count";
    private static final String REQUEST_METHOD = "GET";
    private static final String TYPE = "Content-type";
    private static final String REQUESTED_FORMAT = "application/json";
    private static final String SLASH = "/";
    private static final String EMPTY = "";
    private URL url;
    private HttpURLConnection connection;
    private String rawJson;
    private JsonObject json;
    private JsonObject wifiInformation;

    public OpenAPI() {

    }

    public URL getUrl() {
        return url;
    }

    public void makeURL(URLMaker urlMaker) throws MalformedURLException {
        url = new URL(urlMaker.getURL());
    }
    public void makeHttpConnection() throws IOException{
        connection = (HttpURLConnection) url.openConnection();
    }

    public void connect() throws ProtocolException {
        this.connection.setRequestMethod(REQUEST_METHOD);
        this.connection.setRequestProperty(TYPE, REQUESTED_FORMAT);
    }
    public void checkConnectionStatus() throws IOException {
        if(!(HttpURLConnection.HTTP_OK == this.connection.getResponseCode())){
            throw new IllegalStateException();
        }
    }
    public void convertToRawJson() throws IOException{
        StringBuilder json = new StringBuilder();

        InputStreamReader requestResult = new InputStreamReader(connection.getInputStream(), FORMAT);
        BufferedReader bufferedReader = new BufferedReader(requestResult);
        String jsonContent;
        while ((jsonContent=bufferedReader.readLine())!=CONTENT_EMPTY){
            json.append(jsonContent);
        }
        this.rawJson =  json.toString();
    }
    private void parseRawJson(){
        this.json = (JsonObject) JsonParser.parseString(this.rawJson);
    }
    private void parseWifiInformationFromJson(){
        String serviceName = UrlElement.SERVICE_TYPE.toString().replace(SLASH, EMPTY);
        this.wifiInformation =  (JsonObject) (this.json.get(serviceName));
    }
    public int getNumberOfTotalData(URLMaker urlMaker){
        try{
            makeURL(urlMaker);
            makeHttpConnection();
            connect();
            checkConnectionStatus();
            convertToRawJson();
            parseRawJson();
            parseWifiInformationFromJson();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return this.wifiInformation.get(NUMBER_OF_TOTAL_DATA).getAsInt();
    }
    public OpenApiRequestPublicWifiRecordDto convertToJson(){
        Gson gson = new Gson();
        parseRawJson();
        parseWifiInformationFromJson();
        return gson.fromJson(this.wifiInformation, OpenApiRequestPublicWifiRecordDto.class);
    }


    public OpenApiRequestPublicWifiRecordDto request(URLMaker urlMaker){
        OpenApiRequestPublicWifiRecordDto publicWifiRecordDto = new OpenApiRequestPublicWifiRecordDto();
        try{
            makeURL(urlMaker);
            makeHttpConnection();
            connect();
            checkConnectionStatus();
            convertToRawJson();
            publicWifiRecordDto = convertToJson();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return publicWifiRecordDto;
    }

}
