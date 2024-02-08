package com.dividend.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.boot.model.naming.IllegalIdentifierException;

@Getter
@AllArgsConstructor
public enum Months {

    JAN("Jan", 1),
    FEB("Feb", 2),
    MAR("Mar", 3),
    APR("Apr", 4),
    MAY("May", 5),
    JUN("Jun", 6),
    JUL("Jul", 7),
    AUG("Aug", 8),
    SEP("Sep", 9),
    OCT("Oct", 10),
    NOV("Nov", 11),
    DEC("Dec", 12);

    private final String months;
    private final int num;

    public static int numOfString(String months) {
        for (Months month : Months.values()) {
            if (month.months.equalsIgnoreCase(months)) {
                return month.getNum();
            }
        }
        throw new IllegalArgumentException(months + "은 유효하지 않는 달 입니다.");
    }
    
}
