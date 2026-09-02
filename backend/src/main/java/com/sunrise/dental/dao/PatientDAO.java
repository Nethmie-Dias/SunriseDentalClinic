package com.sunrise.dental.dao;

import com.sunrise.dental.config.DatabaseConfig;
import com.sunrise.dental.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // =========================================================
    // ADD PATIENT
    // =========================================================

    public Patient addPatient(
            Patient patient
    ) {

        String sql =
                "INSERT INTO patients " +
                        "(patient_name, address, contact_number) " +
                        "VALUES (?, ?, ?)";

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
                    patient.getPatientName()
            );

            statement.setString(
                    2,
                    patient.getAddress()
            );

            statement.setString(
                    3,
                    patient.getContactNumber()
            );

            int rows =
                    statement.executeUpdate();

            if (rows == 0) {

                throw new SQLException(
                        "Patient was not inserted."
                );
            }

            try (
                    ResultSet keys =
                            statement.getGeneratedKeys()
            ) {

                if (keys.next()) {

                    int id =
                            keys.getInt(1);

                    return new Patient(
                            id,
                            patient.getPatientName(),
                            patient.getAddress(),
                            patient.getContactNumber()
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "ERROR ADDING PATIENT"
            );

            e.printStackTrace();

            throw new RuntimeException(
                    "Database error while adding patient.",
                    e
            );
        }

        throw new RuntimeException(
                "Patient ID could not be generated."
        );
    }

    // =========================================================
    // GET ALL PATIENTS
    // =========================================================

    public List<Patient> getAllPatients() {

        List<Patient> patients =
                new ArrayList<>();

        String sql =
                "SELECT patient_id, " +
                        "patient_name, " +
                        "address, " +
                        "contact_number " +
                        "FROM patients " +
                        "ORDER BY patient_id DESC";

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Patient patient =
                        new Patient(
                                resultSet.getInt(
                                        "patient_id"
                                ),

                                resultSet.getString(
                                        "patient_name"
                                ),

                                resultSet.getString(
                                        "address"
                                ),

                                resultSet.getString(
                                        "contact_number"
                                )
                        );

                patients.add(patient);
            }

        } catch (SQLException e) {

            System.err.println(
                    "ERROR LOADING PATIENTS"
            );

            e.printStackTrace();

            throw new RuntimeException(
                    "Database error while loading patients.",
                    e
            );
        }

        return patients;
    }

    // =========================================================
    // GET PATIENT BY ID
    // =========================================================

    public Patient getPatientById(
            int patientId
    ) {

        String sql =
                "SELECT patient_id, " +
                        "patient_name, " +
                        "address, " +
                        "contact_number " +
                        "FROM patients " +
                        "WHERE patient_id = ?";

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    patientId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    return new Patient(
                            resultSet.getInt(
                                    "patient_id"
                            ),

                            resultSet.getString(
                                    "patient_name"
                            ),

                            resultSet.getString(
                                    "address"
                            ),

                            resultSet.getString(
                                    "contact_number"
                            )
                    );
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
}