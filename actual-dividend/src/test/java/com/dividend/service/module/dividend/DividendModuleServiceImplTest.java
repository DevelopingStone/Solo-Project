package com.dividend.service.module.dividend;

import com.dividend.model.domain.Dividend;
import com.dividend.persist.DividendRepository;
import com.dividend.persist.entity.DividendEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DividendModuleServiceImplTest {

    @Mock
    private DividendRepository dividendRepository;

    @InjectMocks
    private DividendModuleServiceImpl dividendModuleService;

    @Test
    void success_getDividendsByCompany() {
        // given
        String dividend = "1.175";
        LocalDateTime dateTime = LocalDateTime.of(2017, 5, 17, 0, 0);
        List<DividendEntity> dividendEntities = new ArrayList<>();
        dividendEntities.add(new DividendEntity(0L,1L,dateTime, dividend));
        dividendEntities.add(new DividendEntity(1L,1L,dateTime.plusMonths(3), dividend));
        dividendEntities.add(new DividendEntity(2L,1L,dateTime.plusMonths(3), dividend));
        dividendEntities.add(new DividendEntity(3L,1L,dateTime.plusMonths(3), dividend));
        dividendEntities.add(new DividendEntity(4L,1L,dateTime.plusMonths(3), dividend));
        dividendEntities.add(new DividendEntity(5L,1L,dateTime.plusMonths(3), dividend));

        given(dividendRepository.findAllByCompanyId(anyLong()))
                .willReturn(dividendEntities);
        List<Dividend> dividends = dividendEntities.stream()
                .map(dividendEntity ->
                        new Dividend(dividendEntity.getDate(),
                        dividendEntity.getDividend()))
                .collect(Collectors.toList());
        // when
        List<Dividend> expectedDividends = dividendModuleService.getDividendsByCompany(1L);
        // then
        assertEquals(dividends.size(), expectedDividends.size());
        assertEquals(dividends.get(0).getDividend(), expectedDividends.get(0).getDividend());
        assertEquals(dividends.get(1).getDividend(), expectedDividends.get(1).getDividend());
        assertEquals(dividends.get(2).getDividend(), expectedDividends.get(2).getDividend());
        assertEquals(dividends.get(3).getDividend(), expectedDividends.get(3).getDividend());
        assertEquals(dividends.get(4).getDividend(), expectedDividends.get(4).getDividend());
        assertEquals(dividends.get(5).getDividend(), expectedDividends.get(5).getDividend());

    }
}