package ru.ifmo.nefrology.connecDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ArticleBase {
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
        String create = "CREATE TABLE IF NOT EXISTS Article(" +
                "id SERIAL PRIMARY KEY," +
                "title VARCHAR(50)," +
                "text TEXT" +
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