package com.dividend.config;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MonthsTest {

    @Test
    void numOfString_ValidMonth_ShouldReturnCorrectValue() {
        // give
        String validMonth = "Feb";
        // when
        int result = Months.numOfString(validMonth);
        // then
        assertNotEquals(1, result);
    }

    @Test
    void numOfString_InvalidMonth_ShouldThrowException() {
        // give
        String invalidMonth = "Invalid Value";
        // when & then
        assertThrows(IllegalArgumentException.class, () -> Months.JAN.numOfString(invalidMonth));
    }
}
