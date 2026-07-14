package com.example.ocp.file;

import com.example.ocp.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;

public class FileTest {

    @Test
    public void readFromTestClasspathTest() throws IOException, URISyntaxException {
        URL url = FileTest.class.getClassLoader().getResource("file.txt");
        File file = new File(url.toURI());
        Assertions.assertEquals("halo, i'm a Lohan Lindsey\n", Files.readString(file.toPath()));
    }

    @Test
    public void readFromMainClasspathTest() throws IOException, URISyntaxException {
        URL url = Main.class.getClassLoader().getResource("file.txt");
        File file = new File(url.toURI());
        Assertions.assertEquals("halo, i'm a Lohan Lindsey\n", Files.readString(file.toPath()));
    }

}
