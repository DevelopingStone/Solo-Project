package com.publicWifiSearch.domain.dto.openAPI.jsonParser;

import com.publicWifiSearch.domain.dto.constant.PublicWifiFeature;
import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestAddressDto;
import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestInstallationDto;
import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestPublicWifiDto;
import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestWifiDto;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PublicWifiDeserializer implements JsonDeserializer<OpenApiRequestPublicWifiDto> {

    private static final String MANAGEMENT_ID = PublicWifiFeature.MANAGEMENT_ID.getOpenApiFeatureName();
    @Override
    public OpenApiRequestPublicWifiDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject openApiData = (JsonObject) json.getAsJsonObject();
        OpenApiRequestAddressDto address = new AddressDeserializer().deserialize(json, typeOfT, context);
        OpenApiRequestWifiDto wifi = new WifiDeserializer().deserialize(json, typeOfT, context);
        OpenApiRequestInstallationDto installation = new InstallationDeserializer().deserialize(json, typeOfT, context);


        return OpenApiRequestPublicWifiDto.builder()
                .managementId(openApiData.get(MANAGEMENT_ID).toString())
                .address(address)
                .wifi(wifi)
                .installation(installation)
                .build();
    }
}
