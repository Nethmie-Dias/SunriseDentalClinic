package com.sunrise.dental.service;

import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.dao.DentistDAO;
import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.dao.TreatmentDAO;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.Treatment;

import java.sql.Date;
import java.sql.Time;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;


/**
 * AppointmentService
 *
 * Handles the business logic for appointments.
 *
 * Responsibilities:
 *
 * 1. Validate appointment information
 * 2. Check appointment number
 * 3. Check patient
 * 4. Check dentist
 * 5. Check treatment
 * 6. Prevent double booking
 * 7. Register appointments
 * 8. Search appointments
 * 9. Update appointments
 * 10. Cancel appointments
 * 11. Retrieve all appointments
 */
public class AppointmentService {


    // =========================================================
    // DAO OBJECTS
    // =========================================================

    private final AppointmentDAO appointmentDAO;

    private final PatientDAO patientDAO;

    private final DentistDAO dentistDAO;

    private final TreatmentDAO treatmentDAO;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AppointmentService() {

        appointmentDAO =
                new AppointmentDAO();

        patientDAO =
                new PatientDAO();

        dentistDAO =
                new DentistDAO();

        treatmentDAO =
                new TreatmentDAO();
    }


    // =========================================================
    // REGISTER APPOINTMENT
    // =========================================================

    public int registerAppointment(
            Appointment appointment
    ) {

        // -----------------------------------------------------
        // VALIDATE APPOINTMENT
        // -----------------------------------------------------

        validateAppointment(
                appointment
        );


        // -----------------------------------------------------
        // CHECK APPOINTMENT NUMBER
        // -----------------------------------------------------

        if (
                appointmentDAO.appointmentNumberExists(
                        appointment.getAppointmentNumber()
                )
        ) {

            throw new IllegalArgumentException(
                    "Appointment number already exists."
            );
        }


        // -----------------------------------------------------
        // CHECK PATIENT
        // -----------------------------------------------------

        Patient patient =
                patientDAO.getPatientById(
                        appointment.getPatientId()
                );

        if (patient == null) {

            throw new IllegalArgumentException(
                    "Selected patient does not exist."
            );
        }


        // -----------------------------------------------------
        // CHECK DENTIST
        // -----------------------------------------------------

        Dentist dentist =
                dentistDAO.getDentistById(
                        appointment.getDentistId()
                );

        if (dentist == null) {

            throw new IllegalArgumentException(
                    "Selected dentist does not exist."
            );
        }


        // -----------------------------------------------------
        // CHECK TREATMENT
        // -----------------------------------------------------

        Treatment treatment =
                treatmentDAO.getTreatmentById(
                        appointment.getTreatmentId()
                );

        if (treatment == null) {

            throw new IllegalArgumentException(
                    "Selected treatment does not exist."
            );
        }


        // -----------------------------------------------------
        // CONVERT DATE AND TIME
        // -----------------------------------------------------

        Date appointmentDate =
                Date.valueOf(
                        appointment.getAppointmentDate()
                );

        Time appointmentTime =
                Time.valueOf(
                        appointment.getAppointmentTime()
                );


        // -----------------------------------------------------
        // CHECK DENTIST DOUBLE BOOKING
        // -----------------------------------------------------

        if (
                appointmentDAO.isDentistBooked(
                        appointment.getDentistId(),
                        appointmentDate,
                        appointmentTime
                )
        ) {

            throw new IllegalArgumentException(
                    "This dentist is already booked for the selected date and time."
            );
        }


        // -----------------------------------------------------
        // SET DEFAULT STATUS
        // -----------------------------------------------------

        appointment.setStatus(
                "Scheduled"
        );


        // -----------------------------------------------------
        // SAVE APPOINTMENT
        // -----------------------------------------------------

        return appointmentDAO.addAppointment(
                appointment
        );
    }


    // =========================================================
    // SEARCH APPOINTMENT
    // =========================================================

    public Appointment searchAppointment(
            String appointmentNumber
    ) {

        // -----------------------------------------------------
        // VALIDATE APPOINTMENT NUMBER
        // -----------------------------------------------------

        if (
                appointmentNumber == null
                        ||
                        appointmentNumber.trim().isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "Appointment number is required."
            );
        }


        // -----------------------------------------------------
        // SEARCH DATABASE
        // -----------------------------------------------------

        return appointmentDAO.getAppointmentByNumber(
                appointmentNumber.trim()
        );
    }


    // =========================================================
    // GET ALL APPOINTMENTS
    // =========================================================

    public List<Appointment> getAllAppointments() {

        return appointmentDAO.getAllAppointments();
    }


    // =========================================================
    // UPDATE APPOINTMENT
    // =========================================================

    public String updateAppointment(
            Appointment appointment
    ) {

        // -----------------------------------------------------
        // VALIDATE APPOINTMENT
        // -----------------------------------------------------

        validateAppointmentForUpdate(
                appointment
        );


        // -----------------------------------------------------
        // CHECK EXISTING APPOINTMENT
        // -----------------------------------------------------

        Appointment existingAppointment =
                appointmentDAO.getAppointmentByNumber(
                        appointment.getAppointmentNumber()
                );

        if (
                existingAppointment == null
        ) {

            throw new IllegalArgumentException(
                    "Appointment does not exist."
            );
        }


        // -----------------------------------------------------
        // CHECK APPOINTMENT ID
        // -----------------------------------------------------

        if (
                existingAppointment.getAppointmentId()
                        != appointment.getAppointmentId()
        ) {

            throw new IllegalArgumentException(
                    "Invalid appointment information."
            );
        }


        // -----------------------------------------------------
        // CHECK PATIENT
        // -----------------------------------------------------

        Patient patient =
                patientDAO.getPatientById(
                        appointment.getPatientId()
                );

        if (patient == null) {

            throw new IllegalArgumentException(
                    "Selected patient does not exist."
            );
        }


        // -----------------------------------------------------
        // CHECK DENTIST
        // -----------------------------------------------------

        Dentist dentist =
                dentistDAO.getDentistById(
                        appointment.getDentistId()
                );

        if (dentist == null) {

            throw new IllegalArgumentException(
                    "Selected dentist does not exist."
            );
        }


        // -----------------------------------------------------
        // CHECK TREATMENT
        // -----------------------------------------------------

        Treatment treatment =
                treatmentDAO.getTreatmentById(
                        appointment.getTreatmentId()
                );

        if (treatment == null) {

            throw new IllegalArgumentException(
                    "Selected treatment does not exist."
            );
        }


        // -----------------------------------------------------
        // CONVERT DATE AND TIME
        // -----------------------------------------------------

        Date appointmentDate =
                Date.valueOf(
                        appointment.getAppointmentDate()
                );

        Time appointmentTime =
                Time.valueOf(
                        appointment.getAppointmentTime()
                );


        // -----------------------------------------------------
        // CHECK DENTIST DOUBLE BOOKING
        //
        // Excludes the appointment currently being updated.
        // -----------------------------------------------------

        if (
                appointmentDAO.isDentistBookedForOtherAppointment(
                        appointment.getDentistId(),
                        appointmentDate,
                        appointmentTime,
                        appointment.getAppointmentId()
                )
        ) {

            throw new IllegalArgumentException(
                    "This dentist is already booked for the selected date and time."
            );
        }


        // -----------------------------------------------------
        // KEEP EXISTING STATUS
        // -----------------------------------------------------

        if (
                existingAppointment.getStatus() == null
                        ||
                        existingAppointment.getStatus()
                                .trim()
                                .isEmpty()
        ) {

            appointment.setStatus(
                    "Scheduled"
            );

        } else {

            appointment.setStatus(
                    existingAppointment.getStatus()
            );
        }


        // -----------------------------------------------------
        // UPDATE DATABASE
        // -----------------------------------------------------

        boolean updated =
                appointmentDAO.updateAppointment(
                        appointment
                );


        if (!updated) {

            throw new IllegalArgumentException(
                    "Appointment update failed."
            );
        }


        return "Appointment updated successfully.";
    }


    // =========================================================
    // CANCEL APPOINTMENT
    // =========================================================

    public String cancelAppointment(
            int appointmentId
    ) {

        // -----------------------------------------------------
        // VALIDATE ID
        // -----------------------------------------------------

        if (
                appointmentId <= 0
        ) {

            throw new IllegalArgumentException(
                    "Invalid appointment ID."
            );
        }


        // -----------------------------------------------------
        // FIND APPOINTMENT
        // -----------------------------------------------------

        Appointment appointment =
                findAppointmentById(
                        appointmentId
                );


        if (appointment == null) {

            throw new IllegalArgumentException(
                    "Appointment does not exist."
            );
        }


        // -----------------------------------------------------
        // CHECK CURRENT STATUS
        // -----------------------------------------------------

        if (
                "Cancelled".equalsIgnoreCase(
                        appointment.getStatus()
                )
        ) {

            throw new IllegalArgumentException(
                    "Appointment is already cancelled."
            );
        }


        // -----------------------------------------------------
        // CANCEL
        // -----------------------------------------------------

        boolean cancelled =
                appointmentDAO.cancelAppointment(
                        appointmentId
                );


        if (!cancelled) {

            throw new IllegalArgumentException(
                    "Unable to cancel appointment."
            );
        }


        return "Appointment cancelled successfully.";
    }


    // =========================================================
    // FIND APPOINTMENT BY ID
    // =========================================================

    private Appointment findAppointmentById(
            int appointmentId
    ) {

        List<Appointment> appointments =
                appointmentDAO.getAllAppointments();


        for (
                Appointment appointment :
                appointments
        ) {

            if (
                    appointment.getAppointmentId()
                            == appointmentId
            ) {

                return appointment;
            }
        }


        return null;
    }


    // =========================================================
    // VALIDATE APPOINTMENT FOR REGISTER
    // =========================================================

    private void validateAppointment(
            Appointment appointment
    ) {

        // -----------------------------------------------------
        // NULL CHECK
        // -----------------------------------------------------

        if (
                appointment == null
        ) {

            throw new IllegalArgumentException(
                    "Appointment information is required."
            );
        }


        // -----------------------------------------------------
        // APPOINTMENT NUMBER
        // -----------------------------------------------------

        if (
                isBlank(
                        appointment.getAppointmentNumber()
                )
        ) {

            throw new IllegalArgumentException(
                    "Appointment number is required."
            );
        }


        if (
                !appointment.getAppointmentNumber()
                        .trim()
                        .matches(
                                "^APT[0-9]{3,}$"
                        )
        ) {

            throw new IllegalArgumentException(
                    "Appointment number must be in the format APT001."
            );
        }


        // -----------------------------------------------------
        // PATIENT
        // -----------------------------------------------------

        if (
                appointment.getPatientId() <= 0
        ) {

            throw new IllegalArgumentException(
                    "Please select a valid patient."
            );
        }


        // -----------------------------------------------------
        // DENTIST
        // -----------------------------------------------------

        if (
                appointment.getDentistId() <= 0
        ) {

            throw new IllegalArgumentException(
                    "Please select a valid dentist."
            );
        }


        // -----------------------------------------------------
        // TREATMENT
        // -----------------------------------------------------

        if (
                appointment.getTreatmentId() <= 0
        ) {

            throw new IllegalArgumentException(
                    "Please select a valid treatment."
            );
        }


        // -----------------------------------------------------
        // DATE
        // -----------------------------------------------------

        if (
                appointment.getAppointmentDate()
                        == null
        ) {

            throw new IllegalArgumentException(
                    "Appointment date is required."
            );
        }


        if (
                appointment.getAppointmentDate()
                        .isBefore(
                                LocalDate.now()
                        )
        ) {

            throw new IllegalArgumentException(
                    "Appointment date cannot be in the past."
            );
        }


        // -----------------------------------------------------
        // TIME
        // -----------------------------------------------------

        if (
                appointment.getAppointmentTime()
                        == null
        ) {

            throw new IllegalArgumentException(
                    "Appointment time is required."
            );
        }


        validateClinicTime(
                appointment.getAppointmentTime()
        );
    }


    // =========================================================
    // VALIDATE APPOINTMENT FOR UPDATE
    // =========================================================

    private void validateAppointmentForUpdate(
            Appointment appointment
    ) {

        // -----------------------------------------------------
        // NULL CHECK
        // -----------------------------------------------------

        if (
                appointment == null
        ) {

            throw new IllegalArgumentException(
                    "Appointment information is required."
            );
        }


        // -----------------------------------------------------
        // APPOINTMENT ID
        // -----------------------------------------------------

        if (
                appointment.getAppointmentId() <= 0
        ) {

            throw new IllegalArgumentException(
                    "Invalid appointment ID."
            );
        }


        // -----------------------------------------------------
        // APPOINTMENT NUMBER
        // -----------------------------------------------------

        if (
                isBlank(
                        appointment.getAppointmentNumber()
                )
        ) {

            throw new IllegalArgumentException(
                    "Appointment number is required."
            );
        }


        if (
                !appointment.getAppointmentNumber()
                        .trim()
                        .matches(
                                "^APT[0-9]{3,}$"
                        )
        ) {

            throw new IllegalArgumentException(
                    "Appointment number must be in the format APT001."
            );
        }


        // -----------------------------------------------------
        // PATIENT
        // -----------------------------------------------------

        if (
                appointment.getPatientId() <= 0
        ) {

            throw new IllegalArgumentException(
                    "Please select a valid patient."
            );
        }


        // -----------------------------------------------------
        // DENTIST
        // -----------------------------------------------------

        if (
                appointment.getDentistId() <= 0
        ) {

            throw new IllegalArgumentException(
                    "Please select a valid dentist."
            );
        }


        // -----------------------------------------------------
        // TREATMENT
        // -----------------------------------------------------

        if (
                appointment.getTreatmentId() <= 0
        ) {

            throw new IllegalArgumentException(
                    "Please select a valid treatment."
            );
        }


        // -----------------------------------------------------
        // DATE
        // -----------------------------------------------------

        if (
                appointment.getAppointmentDate()
                        == null
        ) {

            throw new IllegalArgumentException(
                    "Appointment date is required."
            );
        }


        if (
                appointment.getAppointmentDate()
                        .isBefore(
                                LocalDate.now()
                        )
        ) {

            throw new IllegalArgumentException(
                    "Appointment date cannot be in the past."
            );
        }


        // -----------------------------------------------------
        // TIME
        // -----------------------------------------------------

        if (
                appointment.getAppointmentTime()
                        == null
        ) {

            throw new IllegalArgumentException(
                    "Appointment time is required."
            );
        }


        validateClinicTime(
                appointment.getAppointmentTime()
        );
    }


    // =========================================================
    // CLINIC TIME VALIDATION
    // =========================================================

    private void validateClinicTime(
            LocalTime appointmentTime
    ) {

        LocalTime openingTime =
                LocalTime.of(
                        8,
                        0
                );

        LocalTime closingTime =
                LocalTime.of(
                        18,
                        0
                );


        if (
                appointmentTime.isBefore(
                        openingTime
                )
                        ||
                        appointmentTime.isAfter(
                                closingTime
                        )
        ) {

            throw new IllegalArgumentException(
                    "Appointments must be between 08:00 AM and 06:00 PM."
            );
        }
    }


    // =========================================================
    // CHECK BLANK VALUE
    // =========================================================

    private boolean isBlank(
            String value
    ) {

        return value == null
                ||
                value.trim().isEmpty();
    }
}