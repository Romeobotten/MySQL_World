package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private static String URL = "jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    private static String USERNAME = "root";
    private static String PASSWORD = "mysql";

    public static Connection mySqlConnection() throws MySqlException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySqlException(e.getMessage());
        }
        return connection;
    }
}
