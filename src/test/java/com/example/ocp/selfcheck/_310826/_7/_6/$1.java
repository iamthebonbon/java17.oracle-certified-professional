package com.example.ocp.selfcheck._310826._7._6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class $1 {

    @Test
    public void test() {
        var baos = new ByteArrayOutputStream();
        try (baos;
             var dos = new DataOutputStream(baos);
        ) {
            dos.writeUTF("halo");
            dos.write(1);
            dos.writeBoolean(true);
            try (var bais = new ByteArrayInputStream(baos.toByteArray());
                 var dis = new DataInputStream(bais);) {
                Assertions.assertEquals(
                        "halo",
                        dis.readUTF()
                );
                Assertions.assertEquals(
                        1,
                        dis.read()
                );
                Assertions.assertEquals(
                        true,
                        dis.readBoolean()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void testBuff() {
        String s = "houston, com check";
        var bais = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        try (bais;
             var baos = new ByteArrayOutputStream()) {
            int read;
            byte buffer[] = new byte[2];
            while ((read = bais.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            Assertions.assertEquals(
                    s,
                    baos.toString(StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
