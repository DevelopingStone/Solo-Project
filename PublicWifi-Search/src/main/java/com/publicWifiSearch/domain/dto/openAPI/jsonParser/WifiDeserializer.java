package com.publicWifiSearch.domain.dto.openAPI.jsonParser;

import com.publicWifiSearch.domain.dto.constant.PublicWifiFeature;
import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestWifiDto;
import com.google.gson.*;

import java.lang.reflect.Type;

public class WifiDeserializer implements JsonDeserializer<OpenApiRequestWifiDto> {
    @Override
    public OpenApiRequestWifiDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject openApiData = (JsonObject) json.getAsJsonObject();
        String wifiName = PublicWifiFeature.WIFI_NAME.getOpenApiFeatureName();
        String coordinateX = PublicWifiFeature.COORDINATE_X.getOpenApiFeatureName();
        String coordinateY = PublicWifiFeature.COORDINATE_Y.getOpenApiFeatureName();
        String connectionEnvironment=PublicWifiFeature.CONNECTION_ENVIRONMENT.getOpenApiFeatureName();
        String networkType=PublicWifiFeature.NETWORK_TYPE.getOpenApiFeatureName();
        String service = PublicWifiFeature.SERVICE.getOpenApiFeatureName();
        String dateOfWork = PublicWifiFeature.DATE_OF_WORK.getOpenApiFeatureName();
        return OpenApiRequestWifiDto.builder()
                .wifiName(openApiData.get(wifiName).getAsString())
                .coordinateX(openApiData.get(coordinateX).getAsDouble())
                .coordinateY(openApiData.get(coordinateY).getAsDouble())
                .connectionEnvironment(openApiData.get(connectionEnvironment).getAsString())
                .networkType(openApiData.get(networkType).getAsString())
                .service(openApiData.get(service).getAsString())
                .dateOfWork(openApiData.get(dateOfWork).getAsString())
                .build();
    }
}