package com.example.ocp.synchronizeds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VolatileTest {

    private static volatile int tankFuel = 10;  // working program due to volatile

    @Test
    public void test() throws InterruptedException {
        class GasTank extends Thread {
            @Override
            public void run() {
                while (VolatileTest.tankFuel > 0) {
                    System.out.println("Tank available fuel: " + VolatileTest.tankFuel);
                    VolatileTest.tankFuel--;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        class GasPump extends Thread {
            @Override
            public void run() {
                int fuel = VolatileTest.tankFuel;
                while (fuel > 0) {
                    if (fuel != VolatileTest.tankFuel) {
                        System.out.println("Pump available fuel: " + VolatileTest.tankFuel);
                        fuel = VolatileTest.tankFuel;
                    }
                }
            }
        }

        GasTank gasTank = new GasTank();
        GasPump gasPump = new GasPump();

        gasPump.start();
        gasTank.start();

        gasTank.join();
        gasPump.join();

        Assertions.assertEquals(0, VolatileTest.tankFuel);
    }

}
