package com.example.ocp.ethuware;

import java.util.ArrayList;
import java.util.List;

public class $3087 {


    public static class A {

        List<CharSequence> r() {
            return new ArrayList<CharSequence>();
        }

        List<? extends CharSequence> rr() {
            return new ArrayList<String>();
        }

        List<CharSequence> p(List<String> s) {
            return new ArrayList<CharSequence>();
        }
    }

    public static class B extends A {
        ArrayList<CharSequence> r() {
            return new ArrayList<CharSequence>();
        }

        List<String> rr() {
            return new ArrayList<String>();
        }


    }
}
