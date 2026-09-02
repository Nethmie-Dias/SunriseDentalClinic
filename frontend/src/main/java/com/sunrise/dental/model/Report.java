package com.sunrise.dental.model;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * =========================================================
 * SUNRISE DENTAL CLINIC
 * REPORT MODEL
 * =========================================================
 *
 * Model used by the frontend to receive report data
 * from the backend REST API.
 *
 * Supports:
 * 1. Appointment Reports
 * 2. Patient Reports
 * 3. Billing Reports
 *
 * The @JsonAlias annotations allow this single model
 * to correctly handle the field names returned by
 * the backend APIs.
 *
 * =========================================================
 */
public class Report {

    // =========================================================
    // GENERAL / COMMON INFORMATION
    // =========================================================

    private int id;

    private int patientId;

    private int billId;

    private String patientName;

    private String address;

    private String contactNumber;


    // =========================================================
    // APPOINTMENT INFORMATION
    // =========================================================

    private String appointmentNumber;

    private String dentistName;

    private String treatmentName;

    private String appointmentDate;

    private String appointmentTime;

    private String appointmentStatus;


    // =========================================================
    // BILLING INFORMATION
    // =========================================================

    private double treatmentCost;

    private double consultationFee;

    private double totalAmount;

    private String paymentStatus;

    private String paymentDate;

    private String createdAt;


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================

    public Report() {
    }


    // =========================================================
    // ID
    // =========================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    // BILL ID
    // =========================================================

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
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
    // APPOINTMENT NUMBER
    // =========================================================

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


    // =========================================================
    // APPOINTMENT TIME
    // =========================================================

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }


    // =========================================================
    // APPOINTMENT STATUS
    // =========================================================
    //
    // Backend returns:
    // "status"
    //
    // The JsonAlias annotation allows Jackson to map
    // backend "status" -> appointmentStatus.
    //
    // =========================================================

    @JsonAlias("status")
    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    @JsonAlias("status")
    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }


    // =========================================================
    // TREATMENT COST
    // =========================================================

    public double getTreatmentCost() {
        return treatmentCost;
    }

    public void setTreatmentCost(double treatmentCost) {
        this.treatmentCost = treatmentCost;
    }


    // =========================================================
    // CONSULTATION FEE
    // =========================================================

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }


    // =========================================================
    // TOTAL AMOUNT
    // =========================================================

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    // =========================================================
    // PAYMENT STATUS
    // =========================================================

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    // =========================================================
    // PAYMENT DATE
    // =========================================================

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }


    // =========================================================
    // CREATED AT
    // =========================================================

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}