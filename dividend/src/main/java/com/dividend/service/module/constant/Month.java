package com.dividend.service.module.constant;

public enum Month {
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

    private final String monthName;
    private final int monthNumber;

    Month(String monthName, int monthNumber) {
        this.monthName = monthName;
        this.monthNumber = monthNumber;
    }

    public static int MonthNameToMonthNumber(String monthName) {
        for (Month month : Month.values()) {
            if (month.monthName.equals(monthName)) {
                return month.monthNumber;
            }
        }
        return -1;
    }


}
