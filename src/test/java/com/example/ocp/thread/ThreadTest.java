package com.example.ocp.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    public void test() throws InterruptedException {
        TestThread testThread = new TestThread();
        Thread threadA = new Thread(testThread);
        Thread threadB = new Thread(testThread);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(testThread.ii);
        Assertions.assertFalse(200000 == testThread.ii);
    }

    public static class TestThread extends Thread {
        private volatile int ii;

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                ii++;
            }
        }
    }

}
