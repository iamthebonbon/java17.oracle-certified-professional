package com.example.ocp.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class CharArrayWriterTest {

    @Test
    public void test() throws IOException, URISyntaxException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        charArrayWriter.write("1234");
        Assertions.assertEquals(
                "[1, 2, 3, 4]",
                Arrays.toString(charArrayWriter.toCharArray())
        );
    }

}
