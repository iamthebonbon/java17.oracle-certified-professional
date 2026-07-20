package com.example.ocp.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTest.class);

    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    @BeforeAll
    public static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    public static void afterAll() {
        postgres.stop();
    }

    @Test
    void connectsAndQueries() throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute("CREATE TABLE employees (id SERIAL PRIMARY KEY, name VARCHAR(100))");
            stmt.execute("INSERT INTO employees (name) VALUES ('Alice')");

            ResultSet rs = stmt.executeQuery("SELECT name FROM employees");
            Assertions.assertTrue(rs.next());
            Assertions.assertEquals("Alice", rs.getString("name"));
        }
    }

    @Test
    void test() {
        try (
                Connection conn = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name FROM employees")
        ) {
            Assertions.assertTrue(rs.next());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        } finally {
            LOGGER.info("I'm here");
        }
    }

}
