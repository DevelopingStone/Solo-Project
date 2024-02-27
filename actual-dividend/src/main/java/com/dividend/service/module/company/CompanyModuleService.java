package com.dividend.service.module.company;

import com.dividend.model.domain.Company;
import com.dividend.persist.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyModuleService {
    boolean checkExistenceByTicker(String ticker);


    CompanyEntity save(Company company);


    Page<CompanyEntity> findAllCompany(Pageable pageable);

    List<CompanyEntity> getCompanyNamesByKeyword(String keyword);


    void delete(CompanyEntity companyEntity);

    CompanyEntity findCompanyByTicker(String ticker);

    CompanyEntity findCompanyByCompanyName(String companyName);

}
