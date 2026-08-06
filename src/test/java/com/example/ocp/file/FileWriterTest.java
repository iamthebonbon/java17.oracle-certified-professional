package com.example.ocp.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {

    @Test
    public void test() throws IOException {
        var fw = new FileWriter("text.txt");
        File file = new File("text.txt");
        file.deleteOnExit();
    }

}
