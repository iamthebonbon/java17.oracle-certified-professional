package com.example.ocp.arraycovariancy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayTest {

    @Test
    public void test() {
        Number a[] = new Number[]{};
        Integer b[] = new Integer[]{0};
        a = b;
        a[0] = 222;
        b = (Integer[]) a;
        Assertions.assertTrue(b[0] != Integer.valueOf(222));
        Assertions.assertTrue(b[0] == Integer.valueOf(222).intValue());
    }

    @Test
    public void testException() {
        Number a[] = new Number[]{};
        Integer b[] = new Integer[]{0};
        a = b;
        Number[] finalA = a;
        Assertions.assertThrows(ArrayStoreException.class, () -> {
            finalA[0] = 222.0;
        });
    }

}
