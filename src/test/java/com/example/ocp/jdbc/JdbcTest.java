package com.example.ocp.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Testcontainers
public class JdbcTest {

    @Container
    private static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

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

}
