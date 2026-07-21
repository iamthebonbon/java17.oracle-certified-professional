package com.example.ocp.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConnectionCloseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionCloseTest.class);

    private static final Connection conn;
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    static {
        postgres.start();
        try {
            conn = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void afterAll() {
        postgres.stop();
    }

    @Test
    @Order(100)
    void createStoredProcTest() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                    select 1;
                    """);
        } finally {
            conn.close();
        }
    }

    @Test
    @Order(200)
    void test() {
        Assertions.assertThrows(PSQLException.class, () -> {
            try (CallableStatement callableStatement = conn.prepareCall("CALL calculate_bonus(?,?,?)")) {
            }
        });

    }

}
