package com.sunrise.dental.dao;

import com.sunrise.dental.config.DatabaseConfig;
import com.sunrise.dental.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BillDAO {

    // =========================================================
    // CREATE BILL
    // =========================================================

    public int createBill(Bill bill) {

        String sql = """
                INSERT INTO bills
                (
                    appointment_id,
                    treatment_cost,
                    consultation_fee,
                    total_amount,
                    payment_status,
                    payment_date
                )
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            // =====================================================
            // APPOINTMENT ID
            // =====================================================

            statement.setInt(
                    1,
                    bill.getAppointmentId()
            );

            // =====================================================
            // TREATMENT COST
            // =====================================================

            statement.setDouble(
                    2,
                    bill.getTreatmentCost()
            );

            // =====================================================
            // CONSULTATION FEE
            // =====================================================

            statement.setDouble(
                    3,
                    bill.getConsultationFee()
            );

            // =====================================================
            // TOTAL AMOUNT
            // =====================================================

            statement.setDouble(
                    4,
                    bill.getTotalAmount()
            );

            // =====================================================
            // PAYMENT STATUS
            // =====================================================

            /*
             * The database uses UNPAID as the initial
             * payment status.
             */

            statement.setString(
                    5,
                    "UNPAID"
            );

            // =====================================================
            // PAYMENT DATE
            // =====================================================

            /*
             * The bill is not paid when it is first created.
             * Therefore payment_date is NULL.
             */

            statement.setNull(
                    6,
                    java.sql.Types.TIMESTAMP
            );

            // =====================================================
            // DEBUG INFORMATION
            // =====================================================

            System.out.println();
            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "CREATING BILL"
            );

            System.out.println(
                    "Appointment ID: "
                            + bill.getAppointmentId()
            );

            System.out.println(
                    "Treatment Cost: "
                            + bill.getTreatmentCost()
            );

            System.out.println(
                    "Consultation Fee: "
                            + bill.getConsultationFee()
            );

            System.out.println(
                    "Total Amount: "
                            + bill.getTotalAmount()
            );

            System.out.println(
                    "Payment Status: UNPAID"
            );

            System.out.println(
                    "========================================"
            );

            // =====================================================
            // EXECUTE INSERT
            // =====================================================

            int rows =
                    statement.executeUpdate();

            if (rows == 0) {

                System.out.println(
                        "Bill INSERT affected 0 rows."
                );

                return -1;
            }

            // =====================================================
            // GET GENERATED BILL ID
            // =====================================================

            try (
                    ResultSet keys =
                            statement.getGeneratedKeys()
            ) {

                if (keys.next()) {

                    int billId =
                            keys.getInt(1);

                    System.out.println(
                            "Bill saved successfully."
                    );

                    System.out.println(
                            "Generated Bill ID: "
                                    + billId
                    );

                    return billId;
                }
            }

        } catch (Exception e) {

            System.err.println();
            System.err.println(
                    "========================================"
            );

            System.err.println(
                    "BILL SAVE DATABASE ERROR"
            );

            System.err.println(
                    "========================================"
            );

            System.err.println(
                    "Error Type: "
                            + e.getClass().getName()
            );

            System.err.println(
                    "Error Message: "
                            + e.getMessage()
            );

            e.printStackTrace();

            System.err.println(
                    "========================================"
            );
        }

        return -1;
    }


    // =========================================================
    // GET BILL BY APPOINTMENT ID
    // =========================================================

    public Bill getBillByAppointmentId(
            int appointmentId
    ) {

        String sql = """
                SELECT
                    bill_id,
                    appointment_id,
                    treatment_cost,
                    consultation_fee,
                    total_amount,
                    payment_status,
                    payment_date,
                    created_at

                FROM bills

                WHERE appointment_id = ?

                ORDER BY bill_id DESC

                LIMIT 1
                """;

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    appointmentId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    Bill bill =
                            new Bill();

                    // =================================================
                    // BILL ID
                    // =================================================

                    bill.setBillId(
                            resultSet.getInt(
                                    "bill_id"
                            )
                    );

                    // =================================================
                    // APPOINTMENT ID
                    // =================================================

                    bill.setAppointmentId(
                            resultSet.getInt(
                                    "appointment_id"
                            )
                    );

                    // =================================================
                    // TREATMENT COST
                    // =================================================

                    bill.setTreatmentCost(
                            resultSet.getDouble(
                                    "treatment_cost"
                            )
                    );

                    // =================================================
                    // CONSULTATION FEE
                    // =================================================

                    bill.setConsultationFee(
                            resultSet.getDouble(
                                    "consultation_fee"
                            )
                    );

                    // =================================================
                    // TOTAL AMOUNT
                    // =================================================

                    bill.setTotalAmount(
                            resultSet.getDouble(
                                    "total_amount"
                            )
                    );

                    // =================================================
                    // BILL DATE
                    // =================================================

                    if (
                            resultSet.getTimestamp(
                                    "created_at"
                            ) != null
                    ) {

                        bill.setBillDate(
                                resultSet
                                        .getTimestamp(
                                                "created_at"
                                        )
                                        .toLocalDateTime()
                        );
                    }

                    return bill;
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Error retrieving bill: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return null;
    }
}
