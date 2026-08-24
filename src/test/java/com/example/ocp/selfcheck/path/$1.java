package com.example.ocp.selfcheck.path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class $1 {

    @Test
    public void relativize() {
        Path p1 = Paths.get("file1.txt");
        Path p2 = Paths.get("file2.txt");
        Path relativize = p1.relativize(p2);
        Assertions.assertEquals(
                "../file2.txt",
                relativize.toString()
        );
    }

    @Test
    public void resolve() {
        Path p1 = Paths.get("file1.txt");
        Path p2 = Paths.get("file2.txt");
        Path relativize = p1.resolve(p2);
        Assertions.assertEquals(
                "file1.txt/file2.txt",
                relativize.toString()
        );
    }

    @Test
    public void resolveSibling() {
        Path p1 = Paths.get("file1.txt");
        Path p2 = Paths.get("file2.txt");
        Path relativize = p1.resolveSibling(p2);
        Assertions.assertEquals(
                "file2.txt",
                relativize.toString()
        );
    }

}
