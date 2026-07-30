package com.example.ocp.path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsTest {

    @Test
    public void testAbsolute() {
        Path pathA = Paths.get("/", "a/b/c/d.txt");
        Assertions.assertTrue(pathA.isAbsolute());
        Assertions.assertTrue(4 == pathA.getNameCount());
        Assertions.assertTrue("c".equals(pathA.getName(2).toString()));
        Assertions.assertTrue("/a/b/c/d.txt".equals(pathA.toAbsolutePath().toString()));
        Assertions.assertTrue("/".equals(pathA.getRoot().toString()));
        Assertions.assertEquals("/a/b/c/d.txt", pathA.toString());
        Assertions.assertTrue("b/c".equals(pathA.subpath(1, 3).toString()));
        Assertions.assertFalse(pathA.subpath(1, 3).isAbsolute());
        Assertions.assertTrue("/a/b/c".equals(pathA.getParent().toString()));
    }

    @Test
    public void testNormalize() {
        Path pathB = Paths.get("/./a/./b/./c/../d.txt");
        Assertions.assertTrue("/a/b/d.txt".equals(pathB.normalize().toString()));
    }

    @Test
    public void testRelatives() {
        Path pathB = Paths.get("/a/b/d.txt");
        Path pathC = Paths.get("/a/halo.txt");
        Assertions.assertTrue(pathB.relativize(pathC).toString().equals("../../halo.txt"));
    }

}
