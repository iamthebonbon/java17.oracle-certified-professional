package com.example.ocp.numberformat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {


    @Test
    public void factoryMethodsReturnDecimalFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat percent = NumberFormat.getPercentInstance(Locale.US);
        NumberFormat integer = NumberFormat.getIntegerInstance(Locale.US);

        // every factory (except getCompactNumberInstance) returns a DecimalFormat
        Assertions.assertTrue(currency instanceof DecimalFormat);
        Assertions.assertTrue(percent instanceof DecimalFormat);
        Assertions.assertTrue(integer instanceof DecimalFormat);
    }

    @Test
    public void percentInstanceMultipliesByHundred() {
        NumberFormat percent = NumberFormat.getPercentInstance(Locale.US);
        Assertions.assertEquals("26%", percent.format(0.256));
    }

    @Test
    public void integerInstanceRoundsHalfEven() {
        NumberFormat integer = NumberFormat.getIntegerInstance(Locale.US);
        // getIntegerInstance uses HALF_EVEN by default — ties break toward the even neighbor
        Assertions.assertEquals("1,235", integer.format(1234.7));   // ordinary rounding, not a tie
        Assertions.assertEquals("1,234", integer.format(1234.5));   // tie -> 1234 is even
        Assertions.assertEquals("1,236", integer.format(1235.5));   // tie -> 1236 is even
    }

    @Test
    public void formatIsLocaleSensitive() {
        NumberFormat us = NumberFormat.getInstance(Locale.US);
        NumberFormat de = NumberFormat.getInstance(Locale.GERMANY);

        Assertions.assertEquals("1,234.5", us.format(1234.5));
        Assertions.assertEquals("1.234,5", de.format(1234.5));  // separators reversed
    }

    @Test
    public void decimalFormatDefaultRoundingModeIsHalfEven() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        Assertions.assertEquals(RoundingMode.HALF_EVEN, df.getRoundingMode());
    }

    @Test
    public void bigDecimalInputAvoidsFloatingPointNoise() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        // exact tie via BigDecimal(String) -> genuine HALF_EVEN behavior, 4 is even
        Assertions.assertEquals("10,123.24", df.format(new BigDecimal("10123.245")));
    }

    @Test
    public void downcastToDecimalFormatExposesPattern() {
        DecimalFormat df = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        Assertions.assertTrue(df.toPattern().contains("#,##0.00"));
    }

}
