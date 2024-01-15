package com.publicWifiSearch.domain.dto.openAPI.jsonParser;

import com.publicWifiSearch.domain.dto.constant.PublicWifiFeature;
import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestAddressDto;
import com.google.gson.*;

import java.lang.reflect.Type;

public class AddressDeserializer implements JsonDeserializer<OpenApiRequestAddressDto> {

    @Override
    public OpenApiRequestAddressDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject openApiData = (JsonObject) json.getAsJsonObject();

        return OpenApiRequestAddressDto.builder()
                .district(openApiData.get(PublicWifiFeature.DISTRICT.getOpenApiFeatureName()).getAsString())
                .roadAddress(openApiData.get(PublicWifiFeature.ROAD_ADDRESS.getOpenApiFeatureName()).getAsString())
                .detailAddress(openApiData.get(PublicWifiFeature.DETAIL_ADDRESS.getOpenApiFeatureName()).getAsString())
                .build();
    }
}
