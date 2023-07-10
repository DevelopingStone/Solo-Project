package com.dividend.service.module.dividend;

import com.dividend.model.domain.Dividend;
import com.dividend.persist.entity.DividendEntity;

import java.util.List;

public interface DividendModuleService {
    void saveDividends(List<DividendEntity> dividendEntities);

    void deleteAllByCompanyId(Long companyId);

    List<Dividend> getDividendsByCompany(Long companyId);
}
