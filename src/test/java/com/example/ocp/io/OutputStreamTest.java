package com.example.ocp.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class OutputStreamTest {

    @Test
    public void test() {
        int[] message = new int[]{114, 101, 97, 100, 32, 97, 98, 111, 117, 116, 32, 65, 83, 67, 73, 73};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int code : message) {
            outputStream.write(code);
        }

        Assertions.assertEquals("read about ASCII", outputStream.toString(StandardCharsets.UTF_8));
    }
}
