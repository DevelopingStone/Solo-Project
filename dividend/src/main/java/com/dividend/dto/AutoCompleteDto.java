package com.dividend.dto;

import com.dividend.persist.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class AutoCompleteDto {

    private List<String> candidates;


    public static AutoCompleteDto from(List<CompanyEntity> companyEntities){
        return AutoCompleteDto.builder()
                .candidates(companyEntities.stream()
                        .map(CompanyEntity::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
