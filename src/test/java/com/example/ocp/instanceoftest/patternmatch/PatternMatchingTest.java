package com.example.ocp.instanceoftest.patternmatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class PatternMatchingTest {

    @Test
    public void test() {
        CountDownLatch l = new CountDownLatch(1);
        AA base = new AA();
        extracted(base, l);
        Assertions.assertTrue(l.getCount() == 0);
    }

    @Test
    public void testShadowTest() {
        CountDownLatch l = new CountDownLatch(1);
        Base base = new AA();
//        if (base instanceof A base) {
//        shadow is not allowed
//        }
    }

    @Test
    public void testTest() {
        CountDownLatch l = new CountDownLatch(1);
        Base base = new AA();
//        if (base instanceof A base) {
//        shadow is not allowed
//        }
    }

    private void extracted(Base base, CountDownLatch latch) {
        if (base instanceof A a && a instanceof AA aa) {
            aa.mAA();
            latch.countDown();
        }
    }

    private void extractedLongHandOperation(Base base, CountDownLatch latch) {
        if (base instanceof B b || base instanceof A a) {
//            if(b!=null) System.out.println(b.power());
//            if(a!=null) System.out.println(a.power());
        }
    }


}

abstract class Base {
    abstract int power();
}

class A extends Base {
    @Override
    int power() {
        return 0;
    }
}

class AA extends A {
    @Override
    int power() {
        return 1;
    }

    void mAA() {
        System.out.println("mAA");
    }
}

class B extends Base {
    @Override
    int power() {
        return 1;
    }
}

