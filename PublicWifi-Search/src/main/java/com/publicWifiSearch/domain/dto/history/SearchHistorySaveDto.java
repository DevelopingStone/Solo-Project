package com.publicWifiSearch.domain.dto.history;

import com.publicWifiSearch.domain.dto.Dto;
import com.publicWifiSearch.domain.model.history.Coordinate;
import com.publicWifiSearch.domain.model.history.SearchHistory;

public class SearchHistorySaveDto implements Dto<SearchHistory> {
    private final int id;
    private final Coordinate coordinate;
    private final String searchTime;

    public SearchHistorySaveDto(int id, Coordinate coordinate, String searchTime) {
        this.id = id;
        this.coordinate = coordinate;
        this.searchTime = searchTime;
    }

    @Override
    public String toString() {
        return "SearchHistorySaveDto{" +
                "id=" + id +
                ", coordinate=" + coordinate +
                ", searchTime='" + searchTime + '\'' +
                '}';
    }

    @Override
    public SearchHistory toEntity() {
        return SearchHistory.builder()
                .id(id)
                .coordinate(coordinate)
                .searchTime(searchTime)
                .build();
    }

}
