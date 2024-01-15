package com.publicWifiSearch.domain.dto.history;

import com.publicWifiSearch.domain.model.history.Coordinate;

public class CoordinateDto {
    private final double userCoordinateX;
    private final double userCoordinateY;

    public CoordinateDto(double userCoordinateX, double userCoordinateY) {
        this.userCoordinateX = userCoordinateX;
        this.userCoordinateY = userCoordinateY;
    }

    public Coordinate toEntity(){
        return Coordinate.builder()
                .x(userCoordinateX)
                .y(userCoordinateY)
                .build();
    }
}
