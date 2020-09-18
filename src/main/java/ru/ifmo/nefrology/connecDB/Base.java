package ru.ifmo.nefrology.connecDB;

import java.sql.*;

public class Base {
    static final String CONNECTION_STR
            = "jdbc:postgresql://localhost:5433/nefro_app";
    static final String LOGIN = "nikita";
    static final String PWD = "nikita";

    public static void main(String[] args) {
        try {
            connection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static void connection() throws ClassNotFoundException, SQLException {
        String create = "CREATE TABLE IF NOT EXISTS UserTable(" +
                "id SERIAL PRIMARY KEY," +
                "login VARCHAR(50) NOT NULL," +
                "password VARCHAR(50) NOT NULL," +
                "firstName VARCHAR(50)," +
                "secondName VARCHAR(50)," +
                "sex VARCHAR(50)" +
                ");";
        // регистрация драйвера
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                CONNECTION_STR, LOGIN, PWD)) {
            try (Statement statement = connection.createStatement()) {
//                int res = statement.executeUpdate(create);
            }
        }
    }

}