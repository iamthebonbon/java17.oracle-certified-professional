package com.example.ocp.records;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    public void test() {
        TestRecord id = new TestRecord(99);
        Assertions.assertEquals(99, id.id());
        Assertions.assertEquals("no-title", id.title());
        TestRecord title = new TestRecord("title");
        Assertions.assertEquals(0, title.id());
        Assertions.assertEquals("title", title.title());
        TestRecord testRecord = new TestRecord(99, "title");
        Assertions.assertEquals(99, testRecord.id());
        Assertions.assertEquals("title", testRecord.title());
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TestRecord(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TestRecord(""));
    }

    @Test
    public void test1() {
        Assertions.assertEquals("title is overridden", new RecordClass(0, "title").name());
    }

    public static record TestRecord(int id, String title) {
        public TestRecord {
            if (id < 0) {
                throw new IllegalArgumentException("id");
            }
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("title");
            }
        }

        public TestRecord(int id) {
            this(id, "no-title");
        }

        public TestRecord(String title) {
            this(0, title);
        }
    }
}
