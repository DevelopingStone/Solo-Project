package com.dividend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class AutoComplete {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
       private List<String> candidates;

        public static AutoComplete.Response from(AutoCompleteDto autoCompleteDto){
            return Response.builder()
                    .candidates(autoCompleteDto.getCandidates())
                    .build();
        }

    }
}
