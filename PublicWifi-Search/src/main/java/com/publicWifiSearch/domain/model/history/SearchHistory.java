package com.publicWifiSearch.domain.model.history;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class SearchHistory {
    private int id;
    private final Coordinate coordinate;
    private final String searchTime;

    public SearchHistory(int id, Coordinate coordinate, String searchTime) {
        this.id = id;
        this.coordinate = coordinate;
        this.searchTime = searchTime;
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "id=" + id +
                ", coordinate=" + coordinate +
                ", searchTime='" + searchTime + '\'' +
                '}';
    }
}
