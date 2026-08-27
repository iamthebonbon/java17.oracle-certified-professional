package com.example.ocp.selfcheck._1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $2 {

    // ------------------------------------------------------------
    // 1. String immutability
    // ------------------------------------------------------------

    @Test
    void stringMethodsReturnNewObject_originalUnchanged() {
        String s = "hello";
        s.toUpperCase(); // return value discarded

        Assertions.assertEquals("hello", s); // s is unchanged

        String upper = s.toUpperCase(); // must capture the result
        Assertions.assertEquals("HELLO", upper);
    }

    // ------------------------------------------------------------
    // 2. String pool and == vs equals()
    // ------------------------------------------------------------

    @Test
    void literalsArePooled() {
        String a = "hello";
        String b = "hello";

        Assertions.assertSame(a, b); // same pooled reference
        Assertions.assertTrue(a == b);
    }

    @Test
    void newStringIsNotPooled() {
        String a = "hello";
        String c = new String("hello");

        Assertions.assertNotSame(a, c);   // different objects
        Assertions.assertFalse(a == c);
        Assertions.assertEquals(a, c);    // but equal content
    }

    @Test
    void internReturnsPooledReference() {
        String a = "hello";
        String c = new String("hello");
        String d = c.intern();

        Assertions.assertSame(a, d);
    }

    @Test
    void compileTimeConstantConcatIsPooled() {
        String a = "hello";
        String e = "hel" + "lo"; // constant folded at compile time

        Assertions.assertSame(a, e);
    }

    @Test
    void runtimeConcatIsNotPooled() {
        String a = "hello";
        String part = "hel";
        String f = part + "lo"; // built at runtime, not pooled

        Assertions.assertNotSame(a, f);
        Assertions.assertEquals(a, f);
    }

    // ------------------------------------------------------------
    // 3. Common String methods
    // ------------------------------------------------------------

    @Test
    void commonStringMethods() {
        String s = "  Hello World  ";

        Assertions.assertEquals(15, s.length());
        Assertions.assertEquals("Hello World", s.trim());
        Assertions.assertEquals("Hello World", s.strip());
        Assertions.assertFalse(s.isEmpty());
        Assertions.assertFalse(s.isBlank());
        Assertions.assertEquals(8, s.indexOf("World"));
        Assertions.assertEquals("  Hello Java  ", s.replace("World", "Java"));
        Assertions.assertEquals(' ', s.charAt(0));
        Assertions.assertTrue(s.contains("World"));
    }

    @Test
    void substringBoundsBehavior() {
        String s = "hello";

        Assertions.assertEquals("", s.substring(5));   // valid — index right after last char
        Assertions.assertEquals("", s.substring(2, 2)); // valid — empty range
        Assertions.assertEquals("llo", s.substring(2));
        Assertions.assertEquals("ell", s.substring(1, 4));

        Assertions.assertThrows(StringIndexOutOfBoundsException.class,
                () -> s.substring(6)); // index beyond length

        Assertions.assertThrows(StringIndexOutOfBoundsException.class,
                () -> s.substring(3, 2)); // start > end
    }

    // ------------------------------------------------------------
    // 4. Text blocks
    // ------------------------------------------------------------

    @Test
    void basicTextBlock() {
        String tb = """
                Hello
                World
                """;

        Assertions.assertEquals("Hello\nWorld\n", tb);
    }

    @Test
    void noTrailingNewlineWhenClosingDelimiterOnSameLine() {
        String tb = """
                Hello""";

        Assertions.assertEquals("Hello", tb);
    }

    @Test
    void noTrailingNewlineWhenClosingDelimiterOnSameLine2() {
        String tb = """
                Hello\
                """;

        Assertions.assertEquals("Hello", tb);
    }

    @Test
    void closingDelimiterPositionAffectsIndentation() {
        // closing """ aligned with content -> minimal stripping
        String tb1 = """
                Hello
                World
                """;
        Assertions.assertEquals("Hello\nWorld\n", tb1);

        // closing """ pushed to column 0 -> less stripped, leading spaces remain
        String tb2 = """
                                Hello
                                World
                """;
        Assertions.assertEquals("                Hello\n                World\n", tb2);
    }

    @Test
    void trailingWhitespaceIsStrippedAutomatically() {
        String tb = """
                Hello\s\s\s
                World
                """;
        // trailing spaces after "Hello" (beyond the explicit \s) are stripped;
        // \s itself forces exactly one literal, protected space
        Assertions.assertEquals("Hello   \nWorld\n", tb);
    }

    @Test
    void backslashSuppressesLineBreak() {
        String tb = """
                This is a long line \
                that continues here.
                """;

        Assertions.assertEquals("This is a long line that continues here.\n", tb);
    }

    @Test
    void escapeSequencesStillWorkInTextBlocks() {
        String tb = """
                Line1\nLine2
                """;
        // \n adds an EXTRA newline beyond the block's own line break
        Assertions.assertEquals("Line1\nLine2\n", tb);
    }

    // ------------------------------------------------------------
    // 5. StringBuilder — mutability
    // ------------------------------------------------------------

    @Test
    void stringBuilderMutatesInPlace() {
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");

        Assertions.assertEquals("Hello World", sb.toString());
    }

    @Test
    void stringBuilderCommonMethods() {
        StringBuilder sb = new StringBuilder("Hello");

        sb.insert(5, ",");
        Assertions.assertEquals("Hello,", sb.toString());

        sb.delete(5, 6);
        Assertions.assertEquals("Hello", sb.toString());

        sb.reverse();
        Assertions.assertEquals("olleH", sb.toString());

        sb.reverse(); // back to "Hello"
        sb.replace(0, 5, "Howdy");
        Assertions.assertEquals("Howdy", sb.toString());

        sb.deleteCharAt(0);
        Assertions.assertEquals("owdy", sb.toString());

        sb.setCharAt(0, 'X');
        Assertions.assertEquals("Xwdy", sb.toString());
    }

    @Test
    void stringBuilderMethodChaining() {
        String result = new StringBuilder()
                .append("Hello")
                .append(" ")
                .append("World")
                .reverse()
                .toString();

        Assertions.assertEquals("dlroW olleH", result);
    }

    @Test
    void stringBuilderEqualsIsIdentityBased() {
        StringBuilder sb1 = new StringBuilder("hello");
        StringBuilder sb2 = new StringBuilder("hello");

        Assertions.assertNotEquals(sb1, sb2); // equals() not overridden -> identity comparison
        Assertions.assertEquals(sb1.toString(), sb2.toString()); // content comparison via String
    }

    @Test
    void stringConcatVsStringBuilderAppend() {
        String s = "abc";
        StringBuilder sb = new StringBuilder("abc");

        s.concat("def");   // return value discarded -> s unchanged
        sb.append("def");  // mutates sb in place

        Assertions.assertEquals("abc", s);
        Assertions.assertEquals("abcdef", sb.toString());
    }

    // ------------------------------------------------------------
    // 6. byte/short/char promotion sanity check (bonus, ties to arithmetic objective)
    // ------------------------------------------------------------

    @Test
    void charConcatenationWithStringPromotesToInt() {
        char c = 'a';
        // c + 1 is int arithmetic (char promoted to int), THEN concatenated as string
        String result = "" + (c + 1);
        Assertions.assertEquals("98", result);

        // vs direct string concatenation with char - no arithmetic promotion happens
        String result2 = "" + c + 1;
        Assertions.assertEquals("a1", result2);
    }

}
