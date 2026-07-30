package com.example.ocp.interfaces;

public class InterfaceInheritaceTest {

    interface Measurement {
        public default int getLength() {
            return 0;
        }
        public static int getLength() {
            return 10;
        }


        public static int getBreadth() {
            return 0;
        }
    }

    interface Size extends Measurement {
        public static final int UNIT = 100;


    }
}
