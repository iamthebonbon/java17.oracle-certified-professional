package com.example.ocp.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.postgresql.PostgreSQLContainer;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UpdateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTest.class);

    private static final DataSource datasource;
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    static {
        postgres.start();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(postgres.getJdbcUrl());
        config.setUsername(postgres.getUsername());
        config.setPassword(postgres.getPassword());
        config.setMaximumPoolSize(10);
        datasource = new HikariDataSource(config);
    }

    @AfterAll
    public static void afterAll() {
        postgres.stop();
    }

    @Test
    @Order(100)
    void createStoredProcTest() {
        try (Connection connection = datasource.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("""
                    create table balance(
                        id bigserial primary key,
                        amount bigint
                    );
                    
                    insert into balance(amount) values (10000);
                    insert into balance(amount) values (10000);
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(200)
    void test() {
        IntStream.range(0, 100)
                .parallel()
                .forEach(v -> {
                    try (Connection connection = datasource.getConnection()) {
                        connection.setAutoCommit(false);
                        try (Statement statement = connection.createStatement()) {
                            ResultSet resultSet = statement.executeQuery(
                                    "select * from balance where id in (1,2) order by id"
                            );

                            List<Map<String, Object>> rows = new ArrayList<>();

                            while (resultSet.next()) {
                                long id = resultSet.getLong("id");
                                BigDecimal amount = resultSet.getBigDecimal("amount");
                                rows.add(Map.of("id", id, "amount", amount));
                            }

                            statement.executeUpdate(String.format(
                                    """
                                            update balance set amount = %s where id = %s
                                            """,
                                    ((BigDecimal) rows.get(0).get("amount")).subtract(BigDecimal.valueOf(100)),
                                    rows.get(0).get("id")
                            ));

                            statement.executeUpdate(String.format(
                                    """
                                            update balance set amount = %s where id = %s
                                            """,
                                    ((BigDecimal) rows.get(0).get("amount")).add(BigDecimal.valueOf(100)),
                                    rows.get(1).get("id")
                            ));

                            connection.commit();
                        } catch (Exception e) {
                            try {
                                connection.rollback();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            throw new RuntimeException(e);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    @Order(300)
    void checks() {
        try (Connection connection = datasource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from balance order by id");

            List<Map<String, Object>> rows = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                BigDecimal balance = resultSet.getBigDecimal("amount");
                rows.add(Map.of("id", id, "amount", balance));
            }

            Assertions.assertNotEquals(BigDecimal.valueOf(0), rows.get(0).get("amount"));
            Assertions.assertNotEquals(BigDecimal.valueOf(20000), rows.get(1).get("amount"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
