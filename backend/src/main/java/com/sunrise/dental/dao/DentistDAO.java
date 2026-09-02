package com.sunrise.dental.dao;

import com.sunrise.dental.config.DatabaseConfig;
import com.sunrise.dental.model.Dentist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DentistDAO {

    // =========================================================
    // ADD DENTIST
    // =========================================================

    public Dentist addDentist(Dentist dentist) {

        String sql =
                "INSERT INTO dentists " +
                        "(dentist_name, specialization, contact_number, email) " +
                        "VALUES (?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            statement.setString(
                    1,
                    dentist.getDentistName()
            );

            statement.setString(
                    2,
                    dentist.getSpecialization()
            );

            statement.setString(
                    3,
                    dentist.getContactNumber()
            );

            statement.setString(
                    4,
                    dentist.getEmail()
            );

            System.out.println(
                    "=================================="
            );

            System.out.println(
                    "ADDING DENTIST TO DATABASE"
            );

            System.out.println(
                    "Name: "
                            + dentist.getDentistName()
            );

            System.out.println(
                    "Specialization: "
                            + dentist.getSpecialization()
            );

            System.out.println(
                    "Contact: "
                            + dentist.getContactNumber()
            );

            System.out.println(
                    "Email: "
                            + dentist.getEmail()
            );

            System.out.println(
                    "=================================="
            );

            int rows =
                    statement.executeUpdate();

            if (rows == 0) {

                throw new SQLException(
                        "No dentist record was inserted."
                );
            }

            try (
                    ResultSet keys =
                            statement.getGeneratedKeys()
            ) {

                if (keys.next()) {

                    int generatedId =
                            keys.getInt(1);

                    dentist.setDentistId(
                            generatedId
                    );

                    System.out.println(
                            "Dentist inserted successfully."
                    );

                    System.out.println(
                            "Generated Dentist ID: "
                                    + generatedId
                    );

                    return dentist;
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "=================================="
            );

            System.err.println(
                    "DENTIST DATABASE INSERT ERROR"
            );

            e.printStackTrace();

            System.err.println(
                    "=================================="
            );

            throw new RuntimeException(
                    "Unable to add dentist: "
                            + e.getMessage(),
                    e
            );
        }

        throw new RuntimeException(
                "Unable to create dentist."
        );
    }

    // =========================================================
    // GET ALL DENTISTS
    // =========================================================

    public List<Dentist> getAllDentists() {

        List<Dentist> dentists =
                new ArrayList<>();

        String sql =
                "SELECT dentist_id, " +
                        "dentist_name, " +
                        "specialization, " +
                        "contact_number, " +
                        "email " +
                        "FROM dentists " +
                        "ORDER BY dentist_id DESC";

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Dentist dentist =
                        new Dentist(
                                resultSet.getInt(
                                        "dentist_id"
                                ),

                                resultSet.getString(
                                        "dentist_name"
                                ),

                                resultSet.getString(
                                        "specialization"
                                ),

                                resultSet.getString(
                                        "contact_number"
                                ),

                                resultSet.getString(
                                        "email"
                                )
                        );

                dentists.add(dentist);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Dentist retrieval error:"
            );

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to retrieve dentists: "
                            + e.getMessage(),
                    e
            );
        }

        return dentists;
    }

    // =========================================================
    // GET DENTIST BY ID
    // =========================================================

    public Dentist getDentistById(
            int dentistId
    ) {

        String sql =
                "SELECT dentist_id, " +
                        "dentist_name, " +
                        "specialization, " +
                        "contact_number, " +
                        "email " +
                        "FROM dentists " +
                        "WHERE dentist_id = ?";

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    dentistId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    return new Dentist(
                            resultSet.getInt(
                                    "dentist_id"
                            ),

                            resultSet.getString(
                                    "dentist_name"
                            ),

                            resultSet.getString(
                                    "specialization"
                            ),

                            resultSet.getString(
                                    "contact_number"
                            ),

                            resultSet.getString(
                                    "email"
                            )
                    );
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to retrieve dentist: "
                            + e.getMessage(),
                    e
            );
        }

        return null;
    }
}