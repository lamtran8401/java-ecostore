package com.ecostore.db;

import java.sql.*;

public class ConnectionDB {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/ecostore";
                String user = "root";
                String password = "123";
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection connection = ConnectionDB.getConnection();
        if (connection != null) {
            System.out.println("Connect success!");
        } else {
            System.out.println("Connect fail!");
        }
    }
}
