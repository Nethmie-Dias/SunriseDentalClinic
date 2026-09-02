package com.sunrise.dental.dao;

import com.sunrise.dental.config.DatabaseConfig;
import com.sunrise.dental.model.Treatment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO {

    // =========================================================
    // ADD TREATMENT
    // =========================================================

    public Treatment addTreatment(Treatment treatment) {

        String sql =
                "INSERT INTO treatments " +
                        "(treatment_name, description, treatment_cost, " +
                        "consultation_fee, active) " +
                        "VALUES (?, ?, ?, ?, ?)";

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
                    treatment.getTreatmentName()
            );

            statement.setString(
                    2,
                    treatment.getDescription()
            );

            statement.setDouble(
                    3,
                    treatment.getTreatmentCost()
            );

            statement.setDouble(
                    4,
                    treatment.getConsultationFee()
            );

            statement.setBoolean(
                    5,
                    treatment.isActive()
            );

            int rows =
                    statement.executeUpdate();

            if (rows == 0) {

                throw new SQLException(
                        "Creating treatment failed."
                );
            }

            try (
                    ResultSet keys =
                            statement.getGeneratedKeys()
            ) {

                if (keys.next()) {

                    treatment.setTreatmentId(
                            keys.getInt(1)
                    );

                    return treatment;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to add treatment: "
                            + e.getMessage(),
                    e
            );
        }

        throw new RuntimeException(
                "Unable to create treatment."
        );
    }

    // =========================================================
    // GET ALL ACTIVE TREATMENTS
    // =========================================================

    public List<Treatment> getAllTreatments() {

        List<Treatment> treatments =
                new ArrayList<>();

        String sql =
                "SELECT treatment_id, " +
                        "treatment_name, " +
                        "description, " +
                        "treatment_cost, " +
                        "consultation_fee, " +
                        "active " +
                        "FROM treatments " +
                        "WHERE active = TRUE " +
                        "ORDER BY treatment_id DESC";

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Treatment treatment =
                        new Treatment();

                treatment.setTreatmentId(
                        resultSet.getInt(
                                "treatment_id"
                        )
                );

                treatment.setTreatmentName(
                        resultSet.getString(
                                "treatment_name"
                        )
                );

                treatment.setDescription(
                        resultSet.getString(
                                "description"
                        )
                );

                treatment.setTreatmentCost(
                        resultSet.getDouble(
                                "treatment_cost"
                        )
                );

                treatment.setConsultationFee(
                        resultSet.getDouble(
                                "consultation_fee"
                        )
                );

                treatment.setActive(
                        resultSet.getBoolean(
                                "active"
                        )
                );

                treatments.add(
                        treatment
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to retrieve treatments: "
                            + e.getMessage(),
                    e
            );
        }

        return treatments;
    }

    // =========================================================
    // GET TREATMENT BY ID
    // =========================================================

    public Treatment getTreatmentById(
            int treatmentId
    ) {

        String sql =
                "SELECT treatment_id, " +
                        "treatment_name, " +
                        "description, " +
                        "treatment_cost, " +
                        "consultation_fee, " +
                        "active " +
                        "FROM treatments " +
                        "WHERE treatment_id = ?";

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    treatmentId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    Treatment treatment =
                            new Treatment();

                    treatment.setTreatmentId(
                            resultSet.getInt(
                                    "treatment_id"
                            )
                    );

                    treatment.setTreatmentName(
                            resultSet.getString(
                                    "treatment_name"
                            )
                    );

                    treatment.setDescription(
                            resultSet.getString(
                                    "description"
                            )
                    );

                    treatment.setTreatmentCost(
                            resultSet.getDouble(
                                    "treatment_cost"
                            )
                    );

                    treatment.setConsultationFee(
                            resultSet.getDouble(
                                    "consultation_fee"
                            )
                    );

                    treatment.setActive(
                            resultSet.getBoolean(
                                    "active"
                            )
                    );

                    return treatment;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to retrieve treatment: "
                            + e.getMessage(),
                    e
            );
        }

        return null;
    }
}