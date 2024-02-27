package com.dividend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class DeleteCompany {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        @NotNull
        private String name;

        public static DeleteCompany.Response from(CompanyDto companyDto){
            return DeleteCompany.Response.builder()
                    .name(companyDto.getName())
                    .build();
        }

    }
}
