package com.publicWifiSearch.domain.dto.openAPI.jsonParser;

import com.publicWifiSearch.domain.dto.constant.PublicWifiFeature;
import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestInstallationDto;
import com.google.gson.*;

import java.lang.reflect.Type;

public class InstallationDeserializer implements JsonDeserializer<OpenApiRequestInstallationDto> {
    @Override
    public OpenApiRequestInstallationDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject openApiData = (JsonObject) json.getAsJsonObject();
        String installLocation = PublicWifiFeature.INSTALL_LOCATION.getOpenApiFeatureName();
        String installType = PublicWifiFeature.INSTALL_TYPE.getOpenApiFeatureName();
        String installOffice = PublicWifiFeature.INSTALL_OFFICE.getOpenApiFeatureName();
        String installYear = PublicWifiFeature.INSTALL_YEAR.getOpenApiFeatureName();
        String installDivision = PublicWifiFeature.INSTALL_YEAR.getOpenApiFeatureName();
        return OpenApiRequestInstallationDto.builder()
                .installLocation(openApiData.get(installLocation).isJsonNull() ? "null": openApiData.get(installLocation).getAsString())
                .installType(openApiData.get(installType).getAsString())
                .installOffice(openApiData.get(installOffice).getAsString())
                .installYear(openApiData.get(installYear).getAsString())
                .installDivision(openApiData.get(installDivision).getAsString())
                .build();
    }
}