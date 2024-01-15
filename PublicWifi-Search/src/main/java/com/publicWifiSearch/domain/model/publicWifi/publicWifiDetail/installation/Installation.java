package com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Installation implements PublicWifiDetail {

    private String installLocation;
    private String installType;
    private String installOffice;
    private String installYear;
    private String installDivision;

    @Builder
    public Installation(String installLocation, String installType, String installOffice, String installYear, String installDivision) {
        this.installLocation = installLocation;
        this.installType = installType;
        this.installOffice = installOffice;
        this.installYear = installYear;
        this.installDivision = installDivision;
    }

    @Override
    public String toString() {
        return "Installation{" +
                "installLocation='" + installLocation + '\'' +
                ", installType='" + installType + '\'' +
                ", installOffice='" + installOffice + '\'' +
                ", installYear='" + installYear + '\'' +
                ", installDivision='" + installDivision + '\'' +
                '}';
    }

    @Override
    public List<String> toStringAllProperty() {
        return List.of(this.installLocation, this.installType, this.installOffice, this.installYear, this.installDivision);
    }
}
