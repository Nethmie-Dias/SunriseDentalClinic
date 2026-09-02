package com.sunrise.dental.dao;

import com.sunrise.dental.config.DatabaseConfig;
import com.sunrise.dental.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User findUser(
            String username,
            String password
    ) {

        String sql =
                "SELECT user_id, username, password, role " +
                        "FROM users " +
                        "WHERE username = ? AND password = ?";

        System.out.println(
                "Searching for user: " + username
        );

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    username
            );

            statement.setString(
                    2,
                    password
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    User user =
                            new User(
                                    resultSet.getInt(
                                            "user_id"
                                    ),

                                    resultSet.getString(
                                            "username"
                                    ),

                                    resultSet.getString(
                                            "password"
                                    ),

                                    resultSet.getString(
                                            "role"
                                    )
                            );

                    System.out.println(
                            "User found: "
                                    + user.getUsername()
                    );

                    return user;
                }

                System.out.println(
                        "No matching user found."
                );
            }

        } catch (Exception e) {

            System.err.println(
                    "USER DATABASE ERROR"
            );

            e.printStackTrace();
        }

        return null;
    }

    // Compatibility with older code
    public User login(
            String username,
            String password
    ) {

        return findUser(
                username,
                password
        );
    }
}