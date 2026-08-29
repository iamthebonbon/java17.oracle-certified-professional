package com.example.ocp.selfcheck._290826;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class $1 {

    @Test
    public void test() {
        try (var is = this.getClass().getClassLoader().getResourceAsStream("file.txt")) {
            var baos = new ByteArrayOutputStream();
            int i;
            while ((i = is.read()) != -1) {
                baos.write(i);
            }
            String s = new String(baos.toByteArray(), StandardCharsets.UTF_8);
            Assertions.assertTrue(
                    """
                            halo, i'm a Lohan Lindsey
                            """.equals(s)
            );
        } catch (IOException e) {

        }
    }

    @Test
    public void test2() {
        try (var is = this.getClass().getClassLoader().getResourceAsStream("file.txt")) {
            var baos = new ByteArrayOutputStream();
            int read;
            byte buffer[] = new byte[4];
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            String s = new String(baos.toByteArray(), StandardCharsets.UTF_8);
            Assertions.assertTrue(
                    """
                            halo, i'm a Lohan Lindsey
                            """.equals(s)
            );
        } catch (IOException e) {

        }
    }

}
