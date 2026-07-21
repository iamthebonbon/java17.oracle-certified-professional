package com.example.ocp.numberformat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {

    @Test
    public void test() {
        double amount = 200_00.00;
        NumberFormat nf1 = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat nf2 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        NumberFormat nf3 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        Assertions.assertEquals("$20,000.00", nf1.format(amount));
        Assertions.assertEquals("20K", nf2.format(amount));
        Assertions.assertEquals("20 thousand", nf3.format(amount));
    }

}
