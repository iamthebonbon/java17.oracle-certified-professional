package com.example.ocp.selfcheck.numberformat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

public class $1 {

    @Test
    public void getInstance() {
        NumberFormat instance = NumberFormat.getInstance(Locale.UK); // not thread safe
        Assertions.assertEquals(
                "4,123.444", instance.format(4123.4444444)
        );
        Assertions.assertEquals(
                "123,456", instance.format(123456)
        );
    }

    @Test
    public void getCurrencyInstance() {
        NumberFormat instance = NumberFormat.getCurrencyInstance(Locale.JAPAN); // not thread safe
        Assertions.assertEquals(
                "￥4,123", instance.format(4123.4444444)
        );
        Assertions.assertEquals(
                "￥123,456", instance.format(123456)
        );
    }

    @Test
    public void getPercentInstance() {
        NumberFormat instance = NumberFormat.getPercentInstance(Locale.JAPAN); // not thread safe
        Assertions.assertEquals(
                "412,344%", instance.format(4123.4444444)
        );
        Assertions.assertEquals(
                "12,345,600%", instance.format(123456)
        );
    }
}
