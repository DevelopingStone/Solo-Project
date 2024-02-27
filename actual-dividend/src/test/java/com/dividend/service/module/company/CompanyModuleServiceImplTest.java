package com.dividend.service.module.company;

import com.dividend.exception.AbstractException;
import com.dividend.exception.implementation.company.NoCompanyException;
import com.dividend.persist.CompanyRepository;
import com.dividend.persist.entity.CompanyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
class CompanyModuleServiceImplTest {
    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyModuleServiceImpl companyModuleService;

    @Test
    void success_FindCompanyByTicker() {
        // given
        String ticker = "MMM";
        String name = "3M Company";
        CompanyEntity companyEntity = new CompanyEntity(1L, "MMM","3M Company" );
        given(companyRepository.findByTicker(anyString()))
                .willReturn(Optional.of(companyEntity));

        // when
        CompanyEntity company = companyModuleService.findCompanyByTicker(ticker);
        // then
        assertEquals(company.getTicker(), ticker);
        assertEquals(company.getName(), name);
    }

    @Test
    void fail_FindCompanyByTicker() {
        // given
        String ticker = "MMM";
        given(companyRepository.findByTicker(anyString()))
                .willReturn(Optional.empty());

        // when
        AbstractException abstractException = assertThrows(AbstractException.class, () -> companyModuleService.findCompanyByTicker(ticker));
        // then
        assertEquals(abstractException.getClass(), NoCompanyException.class);
    }



    @Test
    void success_FindCompanyByCompanyName() {
        // given
        String ticker = "MMM";
        String name = "3M Company";
        CompanyEntity companyEntity = new CompanyEntity(1L, "MMM","3M Company" );
        given(companyRepository.findByName(anyString()))
                .willReturn(Optional.of(companyEntity));

        // when
        CompanyEntity company = companyModuleService.findCompanyByCompanyName(name);
        // then
        assertEquals(company.getTicker(), ticker);
        assertEquals(company.getName(), name);
    }

    @Test
    void success_findCompanyByCompanyName() {
        // given
        String name = "3M Company";
        given(companyRepository.findByName(anyString()))
                .willReturn(Optional.empty());

        // when
        AbstractException abstractException = assertThrows(AbstractException.class, () -> companyModuleService.findCompanyByCompanyName(name));
        // then
        assertEquals(abstractException.getClass(), NoCompanyException.class);
    }

    @Test
    void success_getCompanyNamesByKeyword() {
        // given
        String ticker = "SPY";
        String name = "SPDR S&P 500 ETF Trust";
        String ticker2 = "DIA";
        String name2 = "SPDR Dow Jones Industrial Average ETF Trust";

        List<CompanyEntity> candidates = new ArrayList<>();
        candidates.add(new CompanyEntity(1L, ticker, name));
        candidates.add(new CompanyEntity(2L, ticker2, name2));
        given(companyRepository.findByNameStartingWithIgnoreCase(anyString(), any()))
                .willReturn(candidates);
        // when
        List<CompanyEntity> expected = companyModuleService.getCompanyNamesByKeyword("s");
        // then
        assertEquals(candidates.size(), expected.size());
        assertEquals(candidates.get(0).getTicker(), ticker);
        assertEquals(candidates.get(0).getName(), name);
        assertEquals(candidates.get(1).getTicker(), ticker2);
        assertEquals(candidates.get(1).getName(), name2);
    }

}