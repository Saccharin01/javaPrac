package LoAExchange.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String HOST = dotenv.get("DATABASE_URL");
    private static final String DB_NAME = dotenv.get("DATABASE_NAME");
    private static final String USER = dotenv.get("DATABASE_USERNAME");
    private static final String PASSWORD = dotenv.get("DATABASE_PASSWORD");

    private static final String URL = """
        jdbc:mariadb://%s/%s
        """.formatted(HOST, DB_NAME);

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}