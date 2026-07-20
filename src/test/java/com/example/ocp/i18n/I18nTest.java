package com.example.ocp.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

public class I18nTest {

    @Test
    public void test() {
        double amount = 1234.5;
        Assertions.assertEquals("$1,234.50", NumberFormat.getCurrencyInstance(Locale.US).format(amount));
        Assertions.assertEquals("1.234,50 €", NumberFormat.getCurrencyInstance(Locale.GERMANY).format(amount));
    }
}
