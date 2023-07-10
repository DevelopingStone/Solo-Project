package com.dividend.dto;

import com.dividend.model.domain.Company;
import com.dividend.model.domain.Dividend;
import com.dividend.model.vo.ScrapedResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapedResultDto {

    private Company company;
    private List<Dividend> dividendList;

    public static ScrapedResultDto from(ScrapedResult scrapedResult){
        return ScrapedResultDto.builder()
                .company(scrapedResult.getCompany())
                .dividendList(scrapedResult.getDividends())
                .build();
    }

}
