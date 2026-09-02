package com.sunrise.dental.dao;

import com.sunrise.dental.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ReportDAO {


    // =========================================================
    // APPOINTMENT REPORT
    // =========================================================

    public List<Map<String, Object>> getAppointmentReport() {

        List<Map<String, Object>> reports =
                new ArrayList<>();

        String sql = """
                SELECT
                    a.appointment_number,
                    p.patient_name,
                    p.contact_number,
                    d.dentist_name,
                    t.treatment_name,
                    a.appointment_date,
                    a.appointment_time,
                    a.status

                FROM appointments a

                INNER JOIN patients p
                    ON a.patient_id = p.patient_id

                INNER JOIN dentists d
                    ON a.dentist_id = d.dentist_id

                INNER JOIN treatments t
                    ON a.treatment_id = t.treatment_id

                ORDER BY
                    a.appointment_date DESC,
                    a.appointment_time DESC
                """;


        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Map<String, Object> row =
                        new HashMap<>();

                row.put(
                        "appointmentNumber",
                        resultSet.getString(
                                "appointment_number"
                        )
                );

                row.put(
                        "patientName",
                        resultSet.getString(
                                "patient_name"
                        )
                );

                row.put(
                        "contactNumber",
                        resultSet.getString(
                                "contact_number"
                        )
                );

                row.put(
                        "dentistName",
                        resultSet.getString(
                                "dentist_name"
                        )
                );

                row.put(
                        "treatmentName",
                        resultSet.getString(
                                "treatment_name"
                        )
                );

                row.put(
                        "appointmentDate",
                        resultSet.getDate(
                                "appointment_date"
                        ).toString()
                );

                row.put(
                        "appointmentTime",
                        resultSet.getTime(
                                "appointment_time"
                        ).toString()
                );

                row.put(
                        "status",
                        resultSet.getString(
                                "status"
                        )
                );

                reports.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return reports;
    }


    // =========================================================
    // PATIENT REPORT
    // =========================================================

    public List<Map<String, Object>> getPatientReport() {

        List<Map<String, Object>> reports =
                new ArrayList<>();

        String sql = """
                SELECT
                    patient_id,
                    patient_name,
                    contact_number,
                    address

                FROM patients

                ORDER BY patient_id DESC
                """;


        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Map<String, Object> row =
                        new HashMap<>();

                row.put(
                        "patientId",
                        resultSet.getInt(
                                "patient_id"
                        )
                );

                row.put(
                        "patientName",
                        resultSet.getString(
                                "patient_name"
                        )
                );

                row.put(
                        "contactNumber",
                        resultSet.getString(
                                "contact_number"
                        )
                );

                row.put(
                        "address",
                        resultSet.getString(
                                "address"
                        )
                );

                reports.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return reports;
    }


    // =========================================================
    // BILLING REPORT
    // =========================================================

    public List<Map<String, Object>> getBillingReport() {

        List<Map<String, Object>> reports =
                new ArrayList<>();

        String sql = """
                SELECT
                    b.bill_id,
                    a.appointment_number,
                    p.patient_name,
                    t.treatment_name,
                    b.treatment_cost,
                    b.consultation_fee,
                    b.total_amount,
                    b.payment_status,
                    b.payment_date,
                    b.created_at

                FROM bills b

                INNER JOIN appointments a
                    ON b.appointment_id =
                       a.appointment_id

                INNER JOIN patients p
                    ON a.patient_id =
                       p.patient_id

                INNER JOIN treatments t
                    ON a.treatment_id =
                       t.treatment_id

                ORDER BY
                    b.bill_id DESC
                """;


        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Map<String, Object> row =
                        new HashMap<>();

                row.put(
                        "billId",
                        resultSet.getInt(
                                "bill_id"
                        )
                );

                row.put(
                        "appointmentNumber",
                        resultSet.getString(
                                "appointment_number"
                        )
                );

                row.put(
                        "patientName",
                        resultSet.getString(
                                "patient_name"
                        )
                );

                row.put(
                        "treatmentName",
                        resultSet.getString(
                                "treatment_name"
                        )
                );

                row.put(
                        "treatmentCost",
                        resultSet.getDouble(
                                "treatment_cost"
                        )
                );

                row.put(
                        "consultationFee",
                        resultSet.getDouble(
                                "consultation_fee"
                        )
                );

                row.put(
                        "totalAmount",
                        resultSet.getDouble(
                                "total_amount"
                        )
                );

                row.put(
                        "paymentStatus",
                        resultSet.getString(
                                "payment_status"
                        )
                );

                if (
                        resultSet.getDate(
                                "payment_date"
                        ) != null
                ) {

                    row.put(
                            "paymentDate",
                            resultSet.getDate(
                                    "payment_date"
                            ).toString()
                    );

                } else {

                    row.put(
                            "paymentDate",
                            ""
                    );
                }

                row.put(
                        "createdAt",
                        resultSet.getTimestamp(
                                "created_at"
                        ).toString()
                );

                reports.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return reports;
    }
}