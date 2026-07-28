package com.example.ocp.localization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DecimalFormatKgTest {

    @Test
    public void testKg() {
        double amount = 10123.23;
        Locale kg = new Locale("kg", "KG");
        NumberFormat currencyInstance = DecimalFormat.getCurrencyInstance(kg);
        Assertions.assertTrue("KGS 10,123.23".equals(currencyInstance.format(amount)));
    }

    @Test
    public void testDefaultKg() {
        double amount = 10123.23;
        Locale kg = new Locale("kg", "KG");
        NumberFormat currencyInstance = DecimalFormat.getInstance(kg);
        Assertions.assertTrue("10,123.23".equals(currencyInstance.format(amount)));
    }

    @Test
    public void testDecimalFormat() {
        double amount = 10123.239;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        Assertions.assertTrue("10,123.24".equals(decimalFormat.format(amount)));
    }

    @Test
    public void testDecimalFormat2() {
        double amount = 1.1;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        Assertions.assertTrue("1.1".equals(decimalFormat.format(amount)));
    }

    @Test
    public void testDecimalFormat3() {
        double amount = 1.10;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.0#");
        Assertions.assertTrue("1.1".equals(decimalFormat.format(amount)));
    }

    @Test
    public void testDecimalFormat4() {
        double amount = 0.11;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.0#");
        Assertions.assertTrue(".11".equals(decimalFormat.format(amount)));
    }

    @Test
    public void testDecimalFormat5() {
        double amount = 00000.11;
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.0#");
        Assertions.assertTrue("0.11".equals(decimalFormat.format(amount)));
    }

}
