package com.dividend.dto;

import com.dividend.model.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class CreateCompany {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        @NotNull
        private String ticker;

        public String getTicker() {
            return this.ticker.trim();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        @NotNull
        private String ticker;
        @NotNull
        private String name;

        public static Response from(Company company){
            return Response.builder()
                    .name(company.getName())
                    .ticker(company.getTicker())
                    .build();
        }

    }

}
