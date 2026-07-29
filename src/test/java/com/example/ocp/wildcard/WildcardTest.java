package com.example.ocp.wildcard;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WildcardTest {

    static class Looby {
    }

    static class Booby {
    }

    static class Dooby extends Booby {
    }

    static class Tooby extends Dooby {
    }

    @Test
    public void testExtends() {
        List<? extends Dooby> extendsList = new ArrayList<Dooby>(
                Arrays.asList(

                        new Tooby(),
                        new Dooby()
                )
        );
    }

    @Test
    public void testSuper() {
        List<? super Dooby> list = new ArrayList<Dooby>(
                Arrays.asList(
                        new Tooby(),
                        new Dooby()
                )
        );
        list.add(new Tooby());
        list.add(new Dooby());
//        list.add(new Booby());
    }

    Booby b = new Booby();
    Tooby t = new Tooby();

    public void do1(List<? super Dooby> dataList) {
//        dataList.add(b);
        dataList.add(t);
    }

    public void do2(List<? extends Dooby> dataList) {
        b = dataList.get(0);
//        t = dataList.get(0);
    }

    public static <T> void copy(List<? extends T> src, List<? super T> dest) {

    }

    @Test
    public void copyTest() {
        List<? super Booby> dest = new ArrayList<Booby>();
        List<? super Dooby> destt = new ArrayList<Dooby>();
        copy(new ArrayList<Tooby>(), dest);
    }

}
