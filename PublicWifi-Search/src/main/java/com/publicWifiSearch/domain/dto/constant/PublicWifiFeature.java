package com.publicWifiSearch.domain.dto.constant;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public enum PublicWifiFeature {
    DISTANCE("", "거리 (Km)", "distance"),
    MANAGEMENT_ID("X_SWIFI_MGR_NO", "관리번호", "managementId"),
    DISTRICT("X_SWIFI_WRDOFC", "자치구", "district"),
    WIFI_NAME("X_SWIFI_MAIN_NM", "와이파이명", "wifiName"),
    ROAD_ADDRESS("X_SWIFI_ADRES1", "도로명 주소", "roadAddress"),
    DETAIL_ADDRESS("X_SWIFI_ADRES2", "상세주소", "detailAddress"),
    INSTALL_LOCATION("X_SWIFI_INSTL_FLOOR", "설치위치(층)", "installLocation"),
    INSTALL_TYPE("X_SWIFI_INSTL_TY", "설치유형", "installType"),
    INSTALL_OFFICE("X_SWIFI_INSTL_MBY", "설치기관", "installOffice"),
    SERVICE("X_SWIFI_SVC_SE", "서비스구분", "service"),
    NETWORK_TYPE("X_SWIFI_CMCWR", "망종류", "networkType"),
    INSTALL_YEAR("X_SWIFI_CNSTC_YEAR", "설치년도","installYear"),

    INSTALL_DIVISION("X_SWIFI_INOUT_DOOR", "실내외구분", "installDivision"),
    CONNECTION_ENVIRONMENT("X_SWIFI_REMARS3", "WIFI접속환경","connectionEnvironment"),
    COORDINATE_X("LNT", "X좌표", "coordinateX"),

    COORDINATE_Y("LAT", "Y좌표", "coordinateY"),
    DATE_OF_WORK("WORK_DTTM", "작업일자", "dateOfWork");

    private final String openApiFeatureName;
    private final String koreanFeatureName;
    private final String domainFeatureName;
    public static final PublicWifiFeature[] publicWifiFeatures = PublicWifiFeature.values();
    PublicWifiFeature(String openApiFeatureName, String koreanFeatureName, String domainFeatureName){
        this.openApiFeatureName = openApiFeatureName;
        this.koreanFeatureName = koreanFeatureName;
        this.domainFeatureName = domainFeatureName;
    }

    public String getOpenApiFeatureName(){
        return this.openApiFeatureName;
    }
    public String getKoreanName(){
        return this.koreanFeatureName;
    }

    public String getDomainFeatureName(){
        return this.domainFeatureName;
    }

    private PublicWifiFeature findByDomainFeature(String domainFeatureName){
        return Arrays.stream(publicWifiFeatures)
                .filter(publicWifiFeature->publicWifiFeature.domainFeatureName.equals(domainFeatureName))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public String findKoreanNameFeature(String domainFeatureName){
        PublicWifiFeature publicWifiFeature = findByDomainFeature(domainFeatureName);
        return publicWifiFeature.koreanFeatureName;
    }

    public static String getFeatureNameForService(){
        Gson gson = new Gson();
        List<String> featureNames = new ArrayList<>();
        for (PublicWifiFeature publicWifiFeature : publicWifiFeatures) {
            featureNames.add(publicWifiFeature.getKoreanName());
        }
        return gson.toJson(featureNames);
    }
}
