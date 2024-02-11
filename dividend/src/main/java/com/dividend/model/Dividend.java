package com.dividend.model;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dividend {

    private Long companyId;

    private LocalDateTime date;

    private double dividend;

}
