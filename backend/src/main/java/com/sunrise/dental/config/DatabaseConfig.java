package com.sunrise.dental.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sunrise_dental"
                    + "?useSSL=false"
                    + "&serverTimezone=UTC";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "";

    private DatabaseConfig() {
    }

    public static Connection getConnection()
            throws SQLException {

        try {

            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

        } catch (ClassNotFoundException e) {

            throw new SQLException(
                    "MySQL JDBC Driver is not available.",
                    e
            );
        }

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}