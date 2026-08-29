package com.example.ocp.selfcheck._290826;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class $2 {

    @Test
    public void test() {
        ResourceBundle bundle = ResourceBundle.getBundle("messages/messages", new Locale("ru", "KG"));
        String pattern = (String) bundle.getObject("pattern");
        String format = MessageFormat.format(pattern, new Object[]{"you", 2.0});
        Assertions.assertTrue(
                "you: $2.00".equals(format)
        );
        MessageFormat format2 = new MessageFormat(pattern, new Locale.Builder()
                .setLanguage("ru")
                .setRegion("KG")
                .build());
        Assertions.assertTrue(
                format2.format(new Object[]{"you", 2.0}).equals("you: 2,00 сом")
        );
    }

}
