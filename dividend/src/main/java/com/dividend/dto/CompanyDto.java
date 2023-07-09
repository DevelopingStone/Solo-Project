package com.dividend.dto;

import com.dividend.model.domain.Company;
import com.dividend.persist.entity.CompanyEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

    private String ticker;
    private String name;

    public static CompanyDto fromDomainModel(Company company) {
        return CompanyDto.builder()
                .name(company.getName())
                .ticker(company.getTicker())
                .build();
    }

    public static CompanyDto fromEntity(CompanyEntity companyEntity) {
        return CompanyDto.builder()
                .name(companyEntity.getName())
                .ticker(companyEntity.getTicker())
                .build();
    }
}
