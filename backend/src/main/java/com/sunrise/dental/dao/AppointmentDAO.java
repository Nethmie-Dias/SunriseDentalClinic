package com.sunrise.dental.dao;

import com.sunrise.dental.config.DatabaseConfig;
import com.sunrise.dental.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // =========================================================
    // CHECK APPOINTMENT NUMBER
    // =========================================================

    public boolean appointmentNumberExists(
            String appointmentNumber
    ) {

        String sql = """
                SELECT appointment_id
                FROM appointments
                WHERE appointment_number = ?
                """;

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    appointmentNumber
            );

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // CHECK DENTIST BOOKING
    // =========================================================

    public boolean isDentistBooked(
            int dentistId,
            Date appointmentDate,
            Time appointmentTime
    ) {

        String sql = """
                SELECT appointment_id
                FROM appointments
                WHERE dentist_id = ?
                AND appointment_date = ?
                AND appointment_time = ?
                AND status = 'Scheduled'
                """;

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

            statement.setDate(
                    2,
                    appointmentDate
            );

            statement.setTime(
                    3,
                    appointmentTime
            );

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // CHECK DENTIST BOOKING FOR UPDATE
    // =========================================================

    public boolean isDentistBookedForOtherAppointment(
            int dentistId,
            Date appointmentDate,
            Time appointmentTime,
            int appointmentId
    ) {

        String sql = """
                SELECT appointment_id
                FROM appointments
                WHERE dentist_id = ?
                AND appointment_date = ?
                AND appointment_time = ?
                AND status = 'Scheduled'
                AND appointment_id <> ?
                """;

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

            statement.setDate(
                    2,
                    appointmentDate
            );

            statement.setTime(
                    3,
                    appointmentTime
            );

            statement.setInt(
                    4,
                    appointmentId
            );

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // ADD APPOINTMENT
    // =========================================================

    public int addAppointment(
            Appointment appointment
    ) {

        String sql = """
                INSERT INTO appointments
                (
                    appointment_number,
                    patient_id,
                    dentist_id,
                    treatment_id,
                    appointment_date,
                    appointment_time,
                    status,
                    notes
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
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

            statement.setString(
                    1,
                    appointment.getAppointmentNumber()
            );

            statement.setInt(
                    2,
                    appointment.getPatientId()
            );

            statement.setInt(
                    3,
                    appointment.getDentistId()
            );

            statement.setInt(
                    4,
                    appointment.getTreatmentId()
            );

            statement.setDate(
                    5,
                    Date.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            statement.setTime(
                    6,
                    Time.valueOf(
                            appointment.getAppointmentTime()
                    )
            );

            statement.setString(
                    7,
                    appointment.getStatus()
            );

            statement.setString(
                    8,
                    appointment.getNotes()
            );

            int rows =
                    statement.executeUpdate();

            if (rows == 0) {

                return -1;
            }

            ResultSet keys =
                    statement.getGeneratedKeys();

            if (keys.next()) {

                return keys.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return -1;
    }


    // =========================================================
    // GET APPOINTMENT BY NUMBER
    // =========================================================

    public Appointment getAppointmentByNumber(
            String appointmentNumber
    ) {

        String sql = """
                SELECT
                    a.appointment_id,
                    a.appointment_number,

                    a.patient_id,
                    p.patient_name,
                    p.address,
                    p.contact_number,

                    a.dentist_id,
                    d.dentist_name,

                    a.treatment_id,
                    t.treatment_name,

                    a.appointment_date,
                    a.appointment_time,
                    a.status,
                    a.notes

                FROM appointments a

                INNER JOIN patients p
                    ON a.patient_id = p.patient_id

                INNER JOIN dentists d
                    ON a.dentist_id = d.dentist_id

                INNER JOIN treatments t
                    ON a.treatment_id = t.treatment_id

                WHERE a.appointment_number = ?
                """;

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    appointmentNumber
            );

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return mapAppointment(
                        resultSet
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // GET ALL APPOINTMENTS
    // =========================================================

    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql = """
                SELECT
                    a.appointment_id,
                    a.appointment_number,

                    a.patient_id,
                    p.patient_name,
                    p.address,
                    p.contact_number,

                    a.dentist_id,
                    d.dentist_name,

                    a.treatment_id,
                    t.treatment_name,

                    a.appointment_date,
                    a.appointment_time,
                    a.status,
                    a.notes

                FROM appointments a

                INNER JOIN patients p
                    ON a.patient_id = p.patient_id

                INNER JOIN dentists d
                    ON a.dentist_id = d.dentist_id

                INNER JOIN treatments t
                    ON a.treatment_id = t.treatment_id

                ORDER BY
                    a.appointment_date,
                    a.appointment_time
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

                appointments.add(
                        mapAppointment(
                                resultSet
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointments;
    }


    // =========================================================
    // UPDATE APPOINTMENT
    // =========================================================

    public boolean updateAppointment(
            Appointment appointment
    ) {

        String sql = """
                UPDATE appointments
                SET
                    patient_id = ?,
                    dentist_id = ?,
                    treatment_id = ?,
                    appointment_date = ?,
                    appointment_time = ?,
                    notes = ?
                WHERE appointment_id = ?
                """;

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    appointment.getPatientId()
            );

            statement.setInt(
                    2,
                    appointment.getDentistId()
            );

            statement.setInt(
                    3,
                    appointment.getTreatmentId()
            );

            statement.setDate(
                    4,
                    Date.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            statement.setTime(
                    5,
                    Time.valueOf(
                            appointment.getAppointmentTime()
                    )
            );

            statement.setString(
                    6,
                    appointment.getNotes()
            );

            statement.setInt(
                    7,
                    appointment.getAppointmentId()
            );

            return statement.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // UPDATE STATUS
    // =========================================================

    public boolean updateStatus(
            int appointmentId,
            String status
    ) {

        String sql = """
                UPDATE appointments
                SET status = ?
                WHERE appointment_id = ?
                """;

        try (
                Connection connection =
                        DatabaseConfig.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    status
            );

            statement.setInt(
                    2,
                    appointmentId
            );

            return statement.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // CANCEL APPOINTMENT
    // =========================================================

    public boolean cancelAppointment(
            int appointmentId
    ) {

        return updateStatus(
                appointmentId,
                "Cancelled"
        );
    }


    // =========================================================
    // MAP RESULTSET TO APPOINTMENT
    // =========================================================

    private Appointment mapAppointment(
            ResultSet resultSet
    ) throws Exception {

        Appointment appointment =
                new Appointment();

        appointment.setAppointmentId(
                resultSet.getInt(
                        "appointment_id"
                )
        );

        appointment.setAppointmentNumber(
                resultSet.getString(
                        "appointment_number"
                )
        );

        appointment.setPatientId(
                resultSet.getInt(
                        "patient_id"
                )
        );

        appointment.setPatientName(
                resultSet.getString(
                        "patient_name"
                )
        );

        appointment.setAddress(
                resultSet.getString(
                        "address"
                )
        );

        appointment.setContactNumber(
                resultSet.getString(
                        "contact_number"
                )
        );

        appointment.setDentistId(
                resultSet.getInt(
                        "dentist_id"
                )
        );

        appointment.setDentistName(
                resultSet.getString(
                        "dentist_name"
                )
        );

        appointment.setTreatmentId(
                resultSet.getInt(
                        "treatment_id"
                )
        );

        appointment.setTreatmentName(
                resultSet.getString(
                        "treatment_name"
                )
        );

        Date date =
                resultSet.getDate(
                        "appointment_date"
                );

        if (date != null) {

            appointment.setAppointmentDate(
                    date.toLocalDate()
            );
        }

        Time time =
                resultSet.getTime(
                        "appointment_time"
                );

        if (time != null) {

            appointment.setAppointmentTime(
                    time.toLocalTime()
            );
        }

        appointment.setStatus(
                resultSet.getString(
                        "status"
                )
        );

        appointment.setNotes(
                resultSet.getString(
                        "notes"
                )
        );

        return appointment;
    }
}