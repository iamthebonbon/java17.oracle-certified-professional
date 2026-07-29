package com.example.ocp.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JdbcResultSetTest {

    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    static {
        postgres.start();
    }

    @Test
    public void test() {
        CountDownLatch latch = new CountDownLatch(1);

        Properties properties = new Properties();
        properties.put("user", postgres.getUsername());
        properties.put("password", postgres.getPassword());

        try (Connection connection = DriverManager.getConnection(postgres.getJdbcUrl(), properties);
             Statement statement = connection.createStatement();
        ) {
            String query = """
                    create table if not exists records(
                    id serial primary key, \
                    title varchar(255)
                    );
                    """; /* implicitly newline \n */
            Assertions.assertTrue(("create table if not exists records(\n" +
                    "id serial primary key, title varchar(255)\n);\n")
                    .equals(query)
            );
            Assertions.assertFalse(
                    statement.execute(query)
            );
            Assertions.assertTrue(1 == statement.executeUpdate("insert into records(title) values('haloe')"));
            try (ResultSet rs = statement.executeQuery("select * from records")) {
                while (rs.next()) {
                    Integer idByName = rs.getInt("id");
                    Integer idByIndex = rs.getInt(1);
                    Assertions.assertTrue(idByName == idByIndex);
                    latch.countDown();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            Assertions.assertTrue(0 == latch.getCount());
            Assertions.assertTrue(Integer.valueOf(0) == latch.getCount());
        }
    }

    @Test
    public void test2() {
        CountDownLatch latch = new CountDownLatch(2);

        Properties properties = new Properties();
        properties.put("user", postgres.getUsername());
        properties.put("password", postgres.getPassword());

        try (Connection connection = DriverManager.getConnection(postgres.getJdbcUrl(), properties);
             Statement statement = connection.createStatement();
        ) {
            String query = """
                    create table if not exists records(
                    id serial primary key, \
                    title varchar(255)
                    );
                    """; /* implicitly newline \n */
            Assertions.assertTrue(("create table if not exists records(" +
                    "\n" +
                    "id serial primary key, title varchar(255)" +
                    "\n);" +
                    "\n")
                    .equals(query)
            );
            Assertions.assertFalse(
                    statement.execute(query)
            );
            Assertions.assertFalse(statement.execute("insert into records(title) values('haloe')"));
            Assertions.assertTrue(statement.execute("select * from records"));
            try (ResultSet rs = statement.getResultSet()) {
                while (rs.next()) {
                    Integer idByName = rs.getInt("id");
                    Integer idByIndex = rs.getInt(1);
                    Assertions.assertTrue(idByName == idByIndex);
                    Assertions.assertTrue(idByName == idByIndex.intValue());
                }
                latch.countDown();
            }
            Assertions.assertFalse(statement.execute("update records set title = 'aloe' where id = 1"));
            Assertions.assertFalse(new Integer(1) == Integer.valueOf(statement.getUpdateCount()));
            Assertions.assertTrue(new Integer(1).equals(statement.getUpdateCount()));
            latch.countDown();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Assertions.assertTrue(0 == latch.getCount());
            Assertions.assertTrue(Integer.valueOf(0) == latch.getCount());
        }
    }


}
