package com.example.ocp.localization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormarKgTest {

    @Test
    public void testKg() {
        double amount = 10123.23;
        Locale kg = new Locale("kg", "KG");
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(kg);
        Assertions.assertTrue("KGS 10,123.23".equals(currencyInstance.format(amount)));
    }

    @Test
    public void testDefaultKg() {
        double amount = 10123.23;
        Locale kg = new Locale("kg", "KG");
        NumberFormat currencyInstance = NumberFormat.getInstance(kg);
        Assertions.assertTrue("10,123.23".equals(currencyInstance.format(amount)));
    }

}
