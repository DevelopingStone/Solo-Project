package com.publicWifiSearch.domain.dto.history;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.publicWifiSearch.domain.dto.constant.SearchHistoryFeature;
import com.publicWifiSearch.domain.model.history.SearchHistory;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
@Getter
public class SearchHistoryResponseDto {
    private final List<SearchHistory> searchHistoryBundle;
    @Builder
    public SearchHistoryResponseDto(List<SearchHistory> searchHistoryBundle) {
        this.searchHistoryBundle = searchHistoryBundle;
    }

    private List<String> collectProperty(SearchHistory searchHistory){
        String id = String.valueOf(searchHistory.getId());
        String coordinateX = String.format("%f", searchHistory.getCoordinate().getX());
        String coordinateY = String.format("%f", searchHistory.getCoordinate().getY());
        return List.of(id, coordinateX, coordinateY, searchHistory.getSearchTime(), "");
    }

    public String toJson() {
        JsonArray searchHistoryJsonBundle = new JsonArray();
        Collections.reverse(searchHistoryBundle);
        for (SearchHistory searchHistory: searchHistoryBundle){
            List<String> searchHistoryProperty = collectProperty(searchHistory);
            SearchHistoryFeature[] searchHistoryFeatures = SearchHistoryFeature.searchHistoryFeatures;
            JsonObject property = new JsonObject();
            for (int i = 0; i < searchHistoryFeatures.length; i++) {

                property.addProperty(searchHistoryFeatures[i].getFeatureName(),searchHistoryProperty.get(i));
            }
            searchHistoryJsonBundle.add(property);
        }
        return searchHistoryJsonBundle.toString();
    }
}
