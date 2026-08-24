package com.example.ocp.selfcheck.files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class $1 {

    @Test
    public void lines() {
        URL resource = $1.class.getClassLoader().getResource("file.txt");
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            Assertions.assertEquals(
                    "[halo, i'm a Lohan Lindsey]",
                    lines.collect(Collectors.toList()).toString()
            );
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readAllLines() {
        URL resource = $1.class.getClassLoader().getResource("file.txt");
        try {
            List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));
            Assertions.assertEquals(
                    "[halo, i'm a Lohan Lindsey]",
                    lines.toString()
            );
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
