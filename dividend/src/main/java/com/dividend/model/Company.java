package com.dividend.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class Company {

    private String ticker;

    private String name;

}
