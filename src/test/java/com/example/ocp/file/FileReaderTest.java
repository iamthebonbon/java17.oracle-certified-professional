package com.example.ocp.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileReaderTest {

    @Test
    public void test() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            FileReader fileReader = new FileReader("a.a");
            try (fileReader) {
                fileReader.read();
            }
        });
    }

}
