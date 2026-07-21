package com.example.ocp.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoredProcedureTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoredProcedureTest.class);

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
        try (
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute("""
                    CREATE OR REPLACE PROCEDURE calculate_bonus(
                                IN emp_id INT,
                                IN performance_score NUMERIC,
                                OUT bonus_amount NUMERIC
                            )
                            LANGUAGE plpgsql
                            AS $$
                            BEGIN
                                bonus_amount := performance_score * 1000;
                            END;
                            $$;
                    """);
        }
    }

    @Test
    @Order(200)
    void test() {
        try (
                CallableStatement callableStatement = conn.prepareCall("CALL calculate_bonus(?,?,?)");
        ) {
            callableStatement.setInt(1, 1);
            callableStatement.setBigDecimal(2, BigDecimal.TEN);
            callableStatement.registerOutParameter(3, Types.DECIMAL);
            callableStatement.execute();

            Assertions.assertEquals(BigDecimal.valueOf(10000), callableStatement.getBigDecimal(3));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            LOGGER.info("I'm here");
        }
    }

}
