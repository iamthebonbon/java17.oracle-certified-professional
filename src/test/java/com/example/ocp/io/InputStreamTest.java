package com.example.ocp.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class InputStreamTest {

    @Test
    public void test() throws IOException, URISyntaxException {
        URL resource = InputStreamTest.class.getClassLoader().getResource("file.txt");
        File file = new File(resource.toURI());
        Assertions.assertEquals(26, file.length());
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ByteArrayOutputStream ous = new ByteArrayOutputStream()) {
            Assertions.assertTrue(fileInputStream.getChannel().isOpen());
            int read;
            byte[] buff = new byte[4];
            while ((read = fileInputStream.read(buff)) != -1) {
                ous.write(buff, 0, read);
            }
            Assertions.assertEquals("halo, i'm a Lohan Lindsey\n", ous.toString(StandardCharsets.UTF_8));
        }

    }

}
