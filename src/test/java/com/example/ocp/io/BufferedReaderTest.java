package com.example.ocp.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BufferedReaderTest {

    @Test
    public void test() throws IOException, URISyntaxException {
        URL resource = BufferedReaderTest.class.getClassLoader().getResource("file.txt");
        File file = new File(resource.toURI());
        Assertions.assertEquals(26, file.length());
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();

        Assertions.assertTrue(fileInputStream.getChannel().isOpen());
        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            int read;
            char[] buff = new char[4];
            while ((read = bufferedReader.read(buff)) != -1) {
                stringBuilder.append(buff, 0, read);
            }
        }
        Assertions.assertFalse(fileInputStream.getChannel().isOpen());
        Assertions.assertEquals("UTF-8", java.nio.charset.Charset.defaultCharset().toString());
        Assertions.assertEquals("halo, i'm a Lohan Lindsey\n", stringBuilder.toString());
    }

}
