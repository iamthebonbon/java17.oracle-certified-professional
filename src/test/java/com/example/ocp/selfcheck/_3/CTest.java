package com.example.ocp.selfcheck._3;

import com.example.ocp.selfcheck._1.I;
import com.example.ocp.selfcheck._2.C1;
import com.example.ocp.selfcheck._2.C2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CTest {

    @Test
    public void test() {
        Assertions.assertTrue(
                ((I) null).I == 0
        );
        Assertions.assertTrue(
                ((C1) null).I == 1
        );
        Assertions.assertTrue(
                ((C2) null).I == 2
        );
    }

}
