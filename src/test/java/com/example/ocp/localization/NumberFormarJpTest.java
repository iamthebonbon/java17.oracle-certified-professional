package com.example.ocp.localization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormarJpTest {

    @Test
    public void testDefaultJp() {
        double amount = 10123.23;
        Locale jp = new Locale("jp", "JP");
        NumberFormat currencyInstance = NumberFormat.getInstance(jp);
        Assertions.assertTrue("10,123.23".equals(currencyInstance.format(amount)));
    }

    @Test
    public void testJp() {
        double amount = 10123.23;
        Locale jp = new Locale("jp", "JP");
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(jp);
        Assertions.assertTrue("JP¥ 10,123".equals(currencyInstance.format(amount)));
    }

}
