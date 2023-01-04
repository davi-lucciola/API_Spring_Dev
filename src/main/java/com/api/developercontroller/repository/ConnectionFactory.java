package com.api.developercontroller.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    final String strConnection = "jdbc:mysql://localhost:3306/dev";
    final String user = "root";
    final String pass = "";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(strConnection, user, pass);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
