package com.dividend.service.module.company;

import com.dividend.exception.implementation.company.NoCompanyException;
import com.dividend.model.domain.Company;
import com.dividend.persist.CompanyRepository;
import com.dividend.persist.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dividend.service.component.nonScheduled.constant.PageableConstant.NUMBER_OF_CONTENT_PER_PAGE;
import static com.dividend.service.component.nonScheduled.constant.PageableConstant.NUMBER_OF_PAGE;

@Component
@AllArgsConstructor
public class CompanyModuleServiceImpl implements CompanyModuleService {
    private final CompanyRepository companyRepository;

    @Transactional
    public boolean checkExistenceByTicker(String ticker) {

        return this.companyRepository.existsByTicker(ticker);
    }


    public CompanyEntity save(Company company) {
        return this.companyRepository.save(new CompanyEntity(company));
    }

    @Transactional
    public Page<CompanyEntity> findAllCompany(Pageable pageable) {
        return this.companyRepository.findAll(pageable);
    }

    @Transactional
    public List<CompanyEntity> getCompanyNamesByKeyword(String keyword) {
        Pageable limit = PageRequest.of(NUMBER_OF_PAGE, NUMBER_OF_CONTENT_PER_PAGE);
        return this.companyRepository.findByNameStartingWithIgnoreCase(keyword, limit);
    }


    public void delete(CompanyEntity companyEntity) {
        this.companyRepository.delete(companyEntity);
    }

    public CompanyEntity findCompanyByTicker(String ticker) {

        return this.companyRepository.findByTicker(ticker.trim())
                .orElseThrow(NoCompanyException::new);
    }

    public CompanyEntity findCompanyByCompanyName(String companyName){
        return companyRepository.findByName(companyName)
                .orElseThrow(NoCompanyException::new);
    }

}
