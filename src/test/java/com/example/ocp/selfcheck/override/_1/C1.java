package com.example.ocp.selfcheck.override._1;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class C1 {

    public <T extends Number> List<T> generics(List<T> ints) throws IOException {
        return new ArrayList<T>();
    }

    public List<? extends Number> wildcards(List<? extends Number> ints) {
        return new ArrayList<Integer>();
    }

    public Number reference(Number number) {
        return BigDecimal.valueOf(1);
    }

    public long primitive(Number number) {
        return Integer.valueOf(1);
    }
}
