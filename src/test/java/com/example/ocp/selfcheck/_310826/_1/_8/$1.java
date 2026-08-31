package com.example.ocp.selfcheck._310826._1._8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class $1 {

    @Test
    public void test() {
        A a = new A(1, 2);
        try (
                var baos = new ByteArrayOutputStream();
                var oos = new ObjectOutputStream(baos);
        ) {
            oos.writeObject(a);
            try (
                    var bais = new ByteArrayInputStream(baos.toByteArray());
                    var ois = new ObjectInputStream(bais);
            ) {
                A readA = (A) ois.readObject();

                Assertions.assertEquals(
                        "A{x=100, y=99}",
                        readA + ""
                );
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static class A implements Serializable {
        private int x;
        private int y;

        public A(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "A{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        private void writeObject(ObjectOutputStream oos) throws IOException {
            this.x = 100;
            oos.defaultWriteObject();
        }

        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            this.y = 99999;
            ois.defaultReadObject();
            this.y = 99;
        }
    }

}
