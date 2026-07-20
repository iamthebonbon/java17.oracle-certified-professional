package com.example.ocp.resourcebundle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest {

    @Test
    public void test() {
        ResourceBundle bundleDefault = ResourceBundle.getBundle("messages/messages");
        ResourceBundle bundleRu = ResourceBundle.getBundle("messages/messages", new Locale("ru"));
        Assertions.assertEquals("halo from default", bundleDefault.getString("halo"));
        Assertions.assertEquals("halo from ru", bundleRu.getString("halo"));
        ResourceBundle bundleKg = ResourceBundle.getBundle("messages/messages", new Locale("kg"));
        Assertions.assertEquals("halo from default", bundleKg.getString("halo"));
    }

}
