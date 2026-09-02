package com.sunrise.dental.model;

import java.time.LocalDateTime;

public class Bill {

    private int billId;
    private int appointmentId;

    private double treatmentCost;
    private double consultationFee;
    private double totalAmount;

    private LocalDateTime billDate;


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================

    public Bill() {
    }


    // =========================================================
    // FULL CONSTRUCTOR
    // =========================================================

    public Bill(
            int billId,
            int appointmentId,
            double treatmentCost,
            double consultationFee,
            double totalAmount,
            LocalDateTime billDate
    ) {

        this.billId =
                billId;

        this.appointmentId =
                appointmentId;

        this.treatmentCost =
                treatmentCost;

        this.consultationFee =
                consultationFee;

        this.totalAmount =
                totalAmount;

        this.billDate =
                billDate;
    }


    // =========================================================
    // BILL ID
    // =========================================================

    public int getBillId() {

        return billId;
    }

    public void setBillId(
            int billId
    ) {

        this.billId =
                billId;
    }


    // =========================================================
    // APPOINTMENT ID
    // =========================================================

    public int getAppointmentId() {

        return appointmentId;
    }

    public void setAppointmentId(
            int appointmentId
    ) {

        this.appointmentId =
                appointmentId;
    }


    // =========================================================
    // TREATMENT COST
    // =========================================================

    public double getTreatmentCost() {

        return treatmentCost;
    }

    public void setTreatmentCost(
            double treatmentCost
    ) {

        this.treatmentCost =
                treatmentCost;
    }


    // =========================================================
    // CONSULTATION FEE
    // =========================================================

    public double getConsultationFee() {

        return consultationFee;
    }

    public void setConsultationFee(
            double consultationFee
    ) {

        this.consultationFee =
                consultationFee;
    }


    // =========================================================
    // TOTAL AMOUNT
    // =========================================================

    public double getTotalAmount() {

        return totalAmount;
    }

    public void setTotalAmount(
            double totalAmount
    ) {

        this.totalAmount =
                totalAmount;
    }


    // =========================================================
    // BILL DATE
    // =========================================================

    public LocalDateTime getBillDate() {

        return billDate;
    }

    public void setBillDate(
            LocalDateTime billDate
    ) {

        this.billDate =
                billDate;
    }
}
