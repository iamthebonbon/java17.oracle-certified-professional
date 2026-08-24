package com.example.ocp.selfcheck.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class $1 {

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
    public void createSchema() {
        try (Connection connection = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
             Statement statement = connection.createStatement()) {
            boolean create = statement.execute(
                    "create table rows(id bigserial primary key, title varchar(255))"
            );
            Assertions.assertFalse(create);

            boolean insert = statement.execute("insert into rows(title) values(" + statement.enquoteLiteral("'');drop table rows;--(") + ")");
            Assertions.assertFalse(insert);
            Assertions.assertTrue(1 == statement.getUpdateCount());

            int count = statement.executeUpdate("insert into rows(title) values(" + statement.enquoteLiteral("'');drop table rows;--(") + ")");
            Assertions.assertTrue(1 == count);

            int counter = 0;

            boolean select = statement.execute("select * from rows");
            Assertions.assertTrue(select);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Assertions.assertTrue("'');drop table rows;--(".equals(resultSet.getString(2)));
                counter++;
            }

            ResultSet rs = statement.executeQuery("select * from rows");
            while (rs.next()) {
                Assertions.assertTrue("'');drop table rows;--(".equals(rs.getString(2)));
                counter++;
            }

            Assertions.assertTrue(4 == counter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
