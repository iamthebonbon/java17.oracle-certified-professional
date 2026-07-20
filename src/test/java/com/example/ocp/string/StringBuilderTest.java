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

}
