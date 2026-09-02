package com.sunrise.dental.config;

import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        try {

            Connection connection =
                    DatabaseConfig.getConnection();

            System.out.println(
                    "================================="
            );

            System.out.println(
                    "DATABASE CONNECTION SUCCESSFUL"
            );

            System.out.println(
                    "Database: "
                            + connection
                            .getCatalog()
            );

            System.out.println(
                    "================================="
            );

            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "DATABASE CONNECTION FAILED"
            );

            e.printStackTrace();
        }
    }
}