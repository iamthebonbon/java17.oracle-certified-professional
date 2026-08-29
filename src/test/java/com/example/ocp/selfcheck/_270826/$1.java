package com.example.ocp.selfcheck._270826;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class $1 {

    // ================================================================
    // 1. Byte streams / character streams
    // ================================================================

    @Test
    void writeAndReadWithFileOutputInputStream() throws IOException {
        Path tempFile = Files.createTempFile("byteio", ".txt");
        try {
            try (FileOutputStream fos = new FileOutputStream(tempFile.toFile())) {
                fos.write("Hello".getBytes(StandardCharsets.UTF_8));
            }

            StringBuilder sb = new StringBuilder();
            try (FileInputStream fis = new FileInputStream(tempFile.toFile())) {
                int data;
                while ((data = fis.read()) != -1) {
                    sb.append((char) data);
                }
            }

            Assertions.assertEquals("Hello", sb.toString());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void writeAndReadWithBufferedReaderWriter() throws IOException {
        Path tempFile = Files.createTempFile("chario", ".txt");
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile.toFile()))) {
                bw.write("line1");
                bw.newLine();
                bw.write("line2");
            }

            List<String> lines;
            try (BufferedReader br = new BufferedReader(new FileReader(tempFile.toFile()))) {
                lines = br.lines().collect(Collectors.toList());
            }

            Assertions.assertEquals(List.of("line1", "line2"), lines);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void printWriterWritesFormattedOutput() throws IOException {
        Path tempFile = Files.createTempFile("printwriter", ".txt");
        try {
            try (PrintWriter pw = new PrintWriter(new FileWriter(tempFile.toFile()))) {
                pw.println("Hello");
                pw.printf("Value: %d%n", 42);
            }

            List<String> lines = Files.readAllLines(tempFile);
            Assertions.assertEquals(List.of("Hello", "Value: 42"), lines);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void filesConvenienceReadWriteMethods() throws IOException {
        Path tempFile = Files.createTempFile("filesapi", ".txt");
        try {
            Files.writeString(tempFile, "Hello NIO");
            String content = Files.readString(tempFile);
            Assertions.assertEquals("Hello NIO", content);

            Files.write(tempFile, List.of("a", "b", "c"));
            List<String> lines = Files.readAllLines(tempFile);
            Assertions.assertEquals(List.of("a", "b", "c"), lines);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    // ================================================================
    // 2. Serialization
    // ================================================================

    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        String name;
        int age;
        transient String password; // excluded from serialization
        static int globalCounter = 100; // never serialized - class-level

        Person(String name, int age, String password) {
            this.name = name;
            this.age = age;
            this.password = password;
        }
    }

    @Test
    void serializeAndDeserializeRoundTrip() throws IOException, ClassNotFoundException {
        Path tempFile = Files.createTempFile("person", ".ser");
        try {
            Person original = new Person("Alice", 30, "secret");

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(tempFile.toFile()))) {
                oos.writeObject(original);
            }

            Person restored;
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(tempFile.toFile()))) {
                restored = (Person) ois.readObject();
            }

            Assertions.assertEquals("Alice", restored.name);
            Assertions.assertEquals(30, restored.age);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void transientFieldIsNotRestoredAfterDeserialization() throws IOException, ClassNotFoundException {
        Path tempFile = Files.createTempFile("person-transient", ".ser");
        try {
            Person original = new Person("Bob", 25, "hunter2");

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(tempFile.toFile()))) {
                oos.writeObject(original);
            }

            Person restored;
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(tempFile.toFile()))) {
                restored = (Person) ois.readObject();
            }

            Assertions.assertNull(restored.password); // transient -> lost, not restored
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void staticFieldChangesAreNotAffectedBySerialization() throws IOException, ClassNotFoundException {
        Path tempFile = Files.createTempFile("person-static", ".ser");
        try {
            Person original = new Person("Carol", 40, "pw");

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(tempFile.toFile()))) {
                oos.writeObject(original);
            }

            Person.globalCounter = 999; // change static state AFTER serialization, before deserialization

            Person restored;
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(tempFile.toFile()))) {
                restored = (Person) ois.readObject();
            }

            // static field is shared at the CLASS level, unaffected by (de)serialization of any instance
            Assertions.assertEquals(999, Person.globalCounter);
            Assertions.assertEquals(999, restored.globalCounter); // same class-level field, accessed via instance
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    static class Address { /* NOT Serializable */
    }

    static class Employee implements Serializable {
        Address address;
    }

    @Test
    void nonSerializableReferencedFieldThrowsAtRuntime() throws IOException {
        Path tempFile = Files.createTempFile("employee", ".ser");
        try {
            Employee e = new Employee();
            e.address = new Address();

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(tempFile.toFile()))) {
                Assertions.assertThrows(NotSerializableException.class, () -> oos.writeObject(e));
            }
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    // ================================================================
    // 3. Path creation and properties
    // ================================================================

    @Test
    void pathCreationVariants() {
        Path p1 = Path.of("a", "b", "c.txt");
        Path p2 = Paths.get("a", "b", "c.txt");

        Assertions.assertEquals(p1, p2);
    }

    @Test
    void pathProperties() {
        Path path = Path.of("dir1", "dir2", "file.txt");

        Assertions.assertEquals(Path.of("file.txt"), path.getFileName());
        Assertions.assertEquals(Path.of("dir1", "dir2"), path.getParent());
        Assertions.assertEquals(3, path.getNameCount());
        Assertions.assertEquals(Path.of("dir1"), path.getName(0));
    }

    @Test
    void pathResolveAndRelativize() {
        Path base = Path.of("dir1", "dir2");
        Path resolved = base.resolve("file.txt");
        Assertions.assertEquals(Path.of("dir1", "dir2", "file.txt"), resolved);

        Path p1 = Path.of("a", "b");
        Path p2 = Path.of("a", "b", "c", "d");
        Path relative = p1.relativize(p2);
        Assertions.assertEquals(Path.of("c", "d"), relative);
    }

    @Test
    void pathNormalize() {
        Path messy = Path.of("dir1/./dir2/../dir3");
        Path normalized = messy.normalize();
        Assertions.assertEquals(Path.of("dir1/dir3"), normalized);
    }

    // ================================================================
    // 4. Creating files / directories
    // ================================================================

    @Test
    void createFileAndCheckExistence() throws IOException {
        Path tempDir = Files.createTempDirectory("createtest");
        try {
            Path file = tempDir.resolve("newfile.txt");
            Assertions.assertFalse(Files.exists(file));

            Files.createFile(file);
            Assertions.assertTrue(Files.exists(file));
            Assertions.assertTrue(Files.isRegularFile(file));

            Assertions.assertThrows(FileAlreadyExistsException.class, () -> Files.createFile(file));
        } finally {
            deleteRecursively(tempDir);
        }
    }

    @Test
    void createDirectoryRequiresExistingParent() throws IOException {
        Path tempDir = Files.createTempDirectory("dirtest");
        try {
            Path deepDir = tempDir.resolve("a").resolve("b");

            Assertions.assertThrows(IOException.class, () -> Files.createDirectory(deepDir)); // parent "a" doesn't exist

            Files.createDirectories(deepDir); // creates all intermediate dirs, no error
            Assertions.assertTrue(Files.isDirectory(deepDir));
        } finally {
            deleteRecursively(tempDir);
        }
    }

    // ================================================================
    // 5. Copy / move / delete
    // ================================================================

    @Test
    void copyMoveDelete() throws IOException {
        Path tempDir = Files.createTempDirectory("copymovetest");
        try {
            Path source = tempDir.resolve("source.txt");
            Files.writeString(source, "content");

            Path copy = tempDir.resolve("copy.txt");
            Files.copy(source, copy);
            Assertions.assertEquals("content", Files.readString(copy));

            Path moved = tempDir.resolve("moved.txt");
            Files.move(copy, moved);
            Assertions.assertFalse(Files.exists(copy));
            Assertions.assertTrue(Files.exists(moved));

            Files.delete(moved);
            Assertions.assertFalse(Files.exists(moved));

            Assertions.assertThrows(NoSuchFileException.class, () -> Files.delete(moved));
            Assertions.assertDoesNotThrow(() -> Files.deleteIfExists(moved)); // no exception, already gone
        } finally {
            deleteRecursively(tempDir);
        }
    }

    // ================================================================
    // 6. Traversing directories
    // ================================================================

    @Test
    void filesListIsOneLevelOnly() throws IOException {
        Path tempDir = Files.createTempDirectory("listtest");
        try {
            Files.createFile(tempDir.resolve("a.txt"));
            Files.createFile(tempDir.resolve("b.txt"));
            Path subDir = Files.createDirectory(tempDir.resolve("sub"));
            Files.createFile(subDir.resolve("nested.txt")); // should NOT appear in Files.list()

            try (Stream<Path> list = Files.list(tempDir)) {
                long count = list.count();
                Assertions.assertEquals(3, count); // a.txt, b.txt, sub (NOT nested.txt)
            }
        } finally {
            deleteRecursively(tempDir);
        }
    }

    @Test
    void filesWalkIsRecursive() throws IOException {
        Path tempDir = Files.createTempDirectory("walktest");
        try {
            Files.createFile(tempDir.resolve("a.txt"));
            Path subDir = Files.createDirectory(tempDir.resolve("sub"));
            Files.createFile(subDir.resolve("nested.txt"));

            try (Stream<Path> walk = Files.walk(tempDir)) {
                long count = walk.count();
                // tempDir itself + a.txt + sub + sub/nested.txt = 4
                Assertions.assertEquals(4, count);
            }
        } finally {
            deleteRecursively(tempDir);
        }
    }

    @Test
    void filesFindWithPredicate() throws IOException {
        Path tempDir = Files.createTempDirectory("findtest");
        try {
            Files.createFile(tempDir.resolve("a.txt"));
            Files.createFile(tempDir.resolve("b.log"));

            try (Stream<Path> found = Files.find(tempDir, Integer.MAX_VALUE,
                    (p, attrs) -> attrs.isRegularFile() && p.toString().endsWith(".txt"))) {
                List<Path> results = found.collect(Collectors.toList());
                Assertions.assertEquals(1, results.size());
                Assertions.assertTrue(results.get(0).toString().endsWith("a.txt"));
            }
        } finally {
            deleteRecursively(tempDir);
        }
    }

    // ================================================================
    // 7. File attributes
    // ================================================================

    @Test
    void basicFileAttributes() throws IOException {
        Path tempFile = Files.createTempFile("attrtest", ".txt");
        try {
            Files.writeString(tempFile, "12345");

            BasicFileAttributes attrs = Files.readAttributes(tempFile, BasicFileAttributes.class);

            Assertions.assertEquals(5, attrs.size());
            Assertions.assertTrue(attrs.isRegularFile());
            Assertions.assertFalse(attrs.isDirectory());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    // ================================================================
    // helper - recursive delete for temp directory cleanup
    // ================================================================

    private void deleteRecursively(Path path) throws IOException {
        if (Files.notExists(path)) return;
        try (Stream<Path> walk = Files.walk(path)) {
            walk.sorted((a, b) -> b.compareTo(a)) // delete children before parents
                    .forEach(p -> {
                        try {
                            Files.deleteIfExists(p);
                        } catch (IOException ignored) {
                        }
                    });
        }
    }

}
