package com.dividend.service.module.dividend;

import com.dividend.model.domain.Dividend;
import com.dividend.persist.DividendRepository;
import com.dividend.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DividendModuleServiceImpl implements DividendModuleService {

    private final DividendRepository dividendRepository;

    public void saveDividends(List<DividendEntity> dividendEntities){
        this.dividendRepository.saveAll(dividendEntities);
    }

    public void deleteAllByCompanyId(Long companyId){
        this.dividendRepository.deleteAllByCompanyId(companyId);
    }

    public List<Dividend> getDividendsByCompany(Long companyId){
        return dividendRepository.findAllByCompanyId(companyId).stream()
                .map(dividendEntity -> new Dividend(dividendEntity.getDate(),
                        dividendEntity.getDividend()))
                .collect(Collectors.toList());

    }

}
