package com.sunrise.dental.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private int appointmentId;

    private String appointmentNumber;

    private int patientId;

    private int dentistId;

    private int treatmentId;

    private String patientName;

    private String address;

    private String contactNumber;

    private String dentistName;

    private String treatmentName;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String status;

    private String notes;


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================

    public Appointment() {
    }


    // =========================================================
    // GET / SET APPOINTMENT ID
    // =========================================================

    public int getAppointmentId() {

        return appointmentId;
    }

    public void setAppointmentId(
            int appointmentId
    ) {

        this.appointmentId = appointmentId;
    }


    // =========================================================
    // GET / SET APPOINTMENT NUMBER
    // =========================================================

    public String getAppointmentNumber() {

        return appointmentNumber;
    }

    public void setAppointmentNumber(
            String appointmentNumber
    ) {

        this.appointmentNumber =
                appointmentNumber;
    }


    // =========================================================
    // GET / SET PATIENT ID
    // =========================================================

    public int getPatientId() {

        return patientId;
    }

    public void setPatientId(
            int patientId
    ) {

        this.patientId = patientId;
    }


    // =========================================================
    // GET / SET DENTIST ID
    // =========================================================

    public int getDentistId() {

        return dentistId;
    }

    public void setDentistId(
            int dentistId
    ) {

        this.dentistId = dentistId;
    }


    // =========================================================
    // GET / SET TREATMENT ID
    // =========================================================

    public int getTreatmentId() {

        return treatmentId;
    }

    public void setTreatmentId(
            int treatmentId
    ) {

        this.treatmentId = treatmentId;
    }


    // =========================================================
    // GET / SET PATIENT NAME
    // =========================================================

    public String getPatientName() {

        return patientName;
    }

    public void setPatientName(
            String patientName
    ) {

        this.patientName = patientName;
    }


    // =========================================================
    // GET / SET ADDRESS
    // =========================================================

    public String getAddress() {

        return address;
    }

    public void setAddress(
            String address
    ) {

        this.address = address;
    }


    // =========================================================
    // GET / SET CONTACT NUMBER
    // =========================================================

    public String getContactNumber() {

        return contactNumber;
    }

    public void setContactNumber(
            String contactNumber
    ) {

        this.contactNumber = contactNumber;
    }


    // =========================================================
    // GET / SET DENTIST NAME
    // =========================================================

    public String getDentistName() {

        return dentistName;
    }

    public void setDentistName(
            String dentistName
    ) {

        this.dentistName = dentistName;
    }


    // =========================================================
    // GET / SET TREATMENT NAME
    // =========================================================

    public String getTreatmentName() {

        return treatmentName;
    }

    public void setTreatmentName(
            String treatmentName
    ) {

        this.treatmentName = treatmentName;
    }


    // =========================================================
    // GET / SET APPOINTMENT DATE
    // =========================================================

    public LocalDate getAppointmentDate() {

        return appointmentDate;
    }

    public void setAppointmentDate(
            LocalDate appointmentDate
    ) {

        this.appointmentDate = appointmentDate;
    }


    // =========================================================
    // GET / SET APPOINTMENT TIME
    // =========================================================

    public LocalTime getAppointmentTime() {

        return appointmentTime;
    }

    public void setAppointmentTime(
            LocalTime appointmentTime
    ) {

        this.appointmentTime = appointmentTime;
    }


    // =========================================================
    // GET / SET STATUS
    // =========================================================

    public String getStatus() {

        return status;
    }

    public void setStatus(
            String status
    ) {

        this.status = status;
    }


    // =========================================================
    // GET / SET NOTES
    // =========================================================

    public String getNotes() {

        return notes;
    }

    public void setNotes(
            String notes
    ) {

        this.notes = notes;
    }


    // =========================================================
    // TO STRING
    // =========================================================

    @Override
    public String toString() {

        return appointmentNumber
                + " - "
                + patientName
                + " - "
                + appointmentDate
                + " "
                + appointmentTime;
    }
}