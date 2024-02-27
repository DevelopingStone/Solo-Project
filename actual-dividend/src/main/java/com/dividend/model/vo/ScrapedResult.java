package com.dividend.model.vo;

import com.dividend.model.domain.Company;
import com.dividend.model.domain.Dividend;
import com.dividend.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ScrapedResult {

    private Company company;
    private List<Dividend> dividends;

    public ScrapedResult() {
        this.dividends = new ArrayList<>();
    }

    public List<DividendEntity> toDividendEntities(Long companyId) {
        return this.dividends.stream()
                .map(dividend -> new DividendEntity(companyId, dividend))
                .collect(Collectors.toList());
    }

}
