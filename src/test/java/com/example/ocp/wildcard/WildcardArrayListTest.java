package com.example.ocp.wildcard;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WildcardArrayListTest {

    @Test
    public void test() {
        var listVar = new ArrayList<>();
        ArrayList<?> arrayList = new ArrayList<>();
        ArrayList<?> arrayList2 = new ArrayList<>();
        ArrayList<?>[] arrayLists = new ArrayList<?>[10]; // array
//        ArrayList<?>[] arrayListss = new ArrayList<>[10]; // it not compile !!!
    }

}
