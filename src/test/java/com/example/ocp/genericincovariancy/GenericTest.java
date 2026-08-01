package com.example.ocp.genericincovariancy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericTest {

    @Test
    public void test() {
        List<Integer> ints = new ArrayList<Integer>();
//        List<Number> nums = ints; not compiled, because generic is invariant
        List<? extends Number> nums = ints;
//        nums.add(1); not allowed, because ? extends Number can be any subtype of Number, i.e. Double
        nums.add(null); // single one exception is allowed.
        List<?> objects = new ArrayList<>();
        Assertions.assertTrue(objects.getClass() == ArrayList.class);
    }


}
