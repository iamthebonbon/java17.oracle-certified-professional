package com.example.ocp.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapTest {

    @Test
    public void test() {
        NavigableMap<String, Integer> map = new TreeMap<>();

        map.put("b", 99);
        map.put("c", 99);
        map.put("a", 99);
        map.put("cc", 99);
        map.put("bb", 99);
        map.put("az", 99);

        Assertions.assertEquals("{a=99, az=99, b=99, bb=99, c=99, cc=99}", map.toString());
        Assertions.assertEquals("{c=99, cc=99}", map.tailMap("c").toString());
        Assertions.assertEquals("{cc=99}", map.tailMap("c", false).toString());
        Assertions.assertEquals("{a=99, az=99}", map.headMap("b").toString());
        Assertions.assertEquals("{a=99, az=99, b=99}", map.headMap("b", true).toString());
        Assertions.assertEquals(99, map.headMap("b", true).remove("b"));
        Assertions.assertEquals(99, map.headMap("c").put("bb", 123));
        Assertions.assertEquals("{a=99, az=99, bb=123, c=99, cc=99}", map.toString());
        Assertions.assertEquals(null, map.put("ba", 11));
        Assertions.assertEquals("{a=99, az=99, ba=11, bb=123, c=99, cc=99}", map.toString());

        Assertions.assertTrue("az".compareTo("b") == -1);


    }

}
