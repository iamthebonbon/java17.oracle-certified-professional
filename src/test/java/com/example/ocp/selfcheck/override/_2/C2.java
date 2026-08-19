package com.example.ocp.selfcheck.override._2;

import com.example.ocp.selfcheck.override._1.C1;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class C2 extends C1 {

    @Override
    public <T extends Number> List<T> generics(List<T> ints) throws AccessDeniedException {
        return new ArrayList<T>();
    }

    public List<Integer> wildcards(List<? extends Number> ints) throws RuntimeException {
        return new ArrayList<Integer>();
    }

    public List<Integer> wildcards(Collection<? extends Number> ints) {
        return new ArrayList<Integer>();
    }

    public Integer reference(Number i) {
        return 0;
    }

    public Integer reference(Integer i) {
        return 0;
    }

}
