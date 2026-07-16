package com.example.ocp.trywithresources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class ReaderTest {

    @Test
    public void test() throws URISyntaxException, FileNotFoundException {
        File file = new File(ReaderTest.class.getClassLoader().getResource("file.txt").toURI());
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int read;
            byte[] buff = new byte[4];
            while ((read = fileInputStream.read(buff)) != -1) {
                out.write(buff, 0, read);
            }
            Assertions.assertEquals("halo, i'm a Lohan Lindsey\n", out.toString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
