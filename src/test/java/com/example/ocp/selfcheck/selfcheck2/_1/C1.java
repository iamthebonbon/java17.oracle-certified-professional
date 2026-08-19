package com.example.ocp.selfcheck.selfcheck2._1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class C1 {

    public <T extends Number> List<T> generics(List<T> ints) throws IOException {
        return new ArrayList<T>();
    }

    public List<? extends Number> wildcards(List<? extends Number> ints) {
        return new ArrayList<Integer>();
    }
}
