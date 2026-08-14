package com.example.ocp.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class _3372Test {
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    {
        postgres.start();
    }

    @Test
    public void test() {
        try (var con = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())) {
            Statement statement = con.createStatement();
            String s = statement.enquoteLiteral("halo");
            String ss = statement.enquoteLiteral("ha'lo");
            Assertions.assertEquals("'halo'", s);
            Assertions.assertEquals("'ha''lo'", ss);
        } catch (SQLException e) {

        }
    }

}
