package com.example.ocp.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringBuilderTest {

    @Test
    public void test() {
        StringBuilder append = new StringBuilder().append("a").append("b").append("@net.com");
        Assertions.assertEquals(
                "ab@net.com",
                append.toString()
        );
    }

    @Test
    public void reverseTest() {
        StringBuilder append = new StringBuilder().append("a").append("b").append("@net.com");
        Assertions.assertEquals(
                "moc.ten@ba",
                append.reverse().toString()
        );
    }

    @Test
    public void textBlockTest() {
        String templateHtml = """
                <html>
                    <body>
                        hello
                    </body>
                </html>
                """;
        Assertions.assertEquals("" +
                "<html>\n" +
                "    <body>\n" +
                "        hello\n" +
                "    </body>\n" +
                "</html>\n", templateHtml);
    }

    @Test
    public void textBlock2Test() {
        String templateHtml = """
                """;
        Assertions.assertEquals("", templateHtml);
        String one = """
                one""";
        Assertions.assertEquals("one", one);
        String two = """
                two
                """;
        Assertions.assertEquals("two\n", two);
    }


    @Test
    public void matchTest() {
        String stringOne = "one";
        Assertions.assertEquals("one", stringOne.intern()); // value is placed to string-pool
    }

    @Test
    public void charAtExceptionTest() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> "one".charAt('0'));
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, () -> "one".charAt('0'));
    }

    @Test
    public void textBlockNewLineTest() {
        String str = """
                0123\
                4567""";
        Assertions.assertEquals("01234567", str);
    }

    @Test
    public void textBlockNewLineBackslashTest() {
        String str = """
                0123
                4567""";
        Assertions.assertEquals("0123\n4567", str);
        Assertions.assertEquals("\n45", str.substring(4, 7));
        Assertions.assertEquals(1, "\\".length());
    }

    static String[] days = {"monday", "tuesday", "wednesday", "thursday",
            "friday", "saturday", "sunday"};

    @Test
    public void main() {

        var index = 0;
        for (var day : days) {
            if (index == 3) {
                break;
            } else {
                continue;
            }
//            index++;
//            if (days[index].length() > 3) {
//                days[index] = day.substring(0, 3);
//            }
        }
        System.out.println(days[index]);
    }

}
