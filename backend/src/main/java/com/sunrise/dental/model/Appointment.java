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
    // FULL CONSTRUCTOR
    // =========================================================

    public Appointment(
            int appointmentId,
            String appointmentNumber,
            int patientId,
            int dentistId,
            int treatmentId,
            String patientName,
            String address,
            String contactNumber,
            String dentistName,
            String treatmentName,
            LocalDate appointmentDate,
            LocalTime appointmentTime,
            String status,
            String notes
    ) {

        this.appointmentId = appointmentId;
        this.appointmentNumber = appointmentNumber;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.treatmentId = treatmentId;
        this.patientName = patientName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.dentistName = dentistName;
        this.treatmentName = treatmentName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.notes = notes;
    }


    // =========================================================
    // APPOINTMENT ID
    // =========================================================

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }


    // =========================================================
    // APPOINTMENT NUMBER
    // =========================================================

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }


    // =========================================================
    // PATIENT ID
    // =========================================================

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }


    // =========================================================
    // DENTIST ID
    // =========================================================

    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
    }


    // =========================================================
    // TREATMENT ID
    // =========================================================

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }


    // =========================================================
    // PATIENT NAME
    // =========================================================

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    // =========================================================
    // ADDRESS
    // =========================================================

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    // =========================================================
    // CONTACT NUMBER
    // =========================================================

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    // =========================================================
    // DENTIST NAME
    // =========================================================

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }


    // =========================================================
    // TREATMENT NAME
    // =========================================================

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }


    // =========================================================
    // APPOINTMENT DATE
    // =========================================================

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


    // =========================================================
    // APPOINTMENT TIME
    // =========================================================

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }


    // =========================================================
    // STATUS
    // =========================================================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // =========================================================
    // NOTES
    // =========================================================

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
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
