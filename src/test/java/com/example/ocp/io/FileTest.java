package com.example.ocp.io;

import com.example.ocp.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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

    @Test
    public void createDirTest() throws IOException {
        File dir = new File(UUID.randomUUID().toString());
        Assertions.assertTrue(dir.mkdirs());
        Assertions.assertTrue(dir.isDirectory());
        Assertions.assertTrue(dir.exists());
        dir.deleteOnExit();
    }

    @Test
    public void createFile() throws IOException {
        File file = new File(UUID.randomUUID().toString());
        file.deleteOnExit();
        if (file.createNewFile()) {
            String path = file.getAbsolutePath();
            Path path1 = Paths.get(path);
            Assertions.assertEquals("/", path1.getRoot().toString());
            Assertions.assertEquals("Users", path1.getName(0).toString());
            try (RandomAccessFile rw = new RandomAccessFile(path1.toString(), "rw")) {
                rw.writeBytes("halo");
            }
            Assertions.assertTrue("halo".equals(Files.readString(path1)));
            try (var file1 = new FileInputStream(path1.toFile());
                 var out = new ByteArrayOutputStream()) {
                int read = 0;
                byte[] buff = new byte[2];
                while ((read = file1.read(buff, 0, buff.length)) != -1) {
                    out.write(buff, 0, read);
                }
                Assertions.assertEquals("halo", out.toString());
            }
            try (RandomAccessFile rw = new RandomAccessFile(path1.toString(), "rw")) {
                rw.seek(rw.length());
                rw.writeBytes("end");
            }
            try (var file1 = new FileInputStream(path1.toFile());
                 var out = new ByteArrayOutputStream()) {
                int read;
                byte[] buff = new byte[2];
                while ((read = file1.read(buff, 0, buff.length)) != -1) {
                    out.write(buff, 0, read);
                }
                Assertions.assertEquals("haloend", out.toString());
            }
        }

    }

}
