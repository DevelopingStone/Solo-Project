package com.dividend.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
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

}
