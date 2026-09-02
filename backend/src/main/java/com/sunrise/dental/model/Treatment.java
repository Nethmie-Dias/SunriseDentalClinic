package com.sunrise.dental.model;

public class Treatment {

    private int treatmentId;

    private String treatmentName;

    private String description;

    private double treatmentCost;

    private double consultationFee;

    private boolean active;


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================

    public Treatment() {

        this.active = true;
    }


    // =========================================================
    // CONSTRUCTOR WITHOUT ID
    // =========================================================

    public Treatment(
            String treatmentName,
            String description,
            double treatmentCost,
            double consultationFee
    ) {

        this.treatmentName =
                treatmentName;

        this.description =
                description;

        this.treatmentCost =
                treatmentCost;

        this.consultationFee =
                consultationFee;

        this.active =
                true;
    }


    // =========================================================
    // CONSTRUCTOR WITH ID
    // =========================================================

    public Treatment(
            int treatmentId,
            String treatmentName,
            String description,
            double treatmentCost,
            double consultationFee,
            boolean active
    ) {

        this.treatmentId =
                treatmentId;

        this.treatmentName =
                treatmentName;

        this.description =
                description;

        this.treatmentCost =
                treatmentCost;

        this.consultationFee =
                consultationFee;

        this.active =
                active;
    }


    // =========================================================
    // TREATMENT ID
    // =========================================================

    public int getTreatmentId() {

        return treatmentId;
    }

    public void setTreatmentId(
            int treatmentId
    ) {

        this.treatmentId =
                treatmentId;
    }


    // =========================================================
    // TREATMENT NAME
    // =========================================================

    public String getTreatmentName() {

        return treatmentName;
    }

    public void setTreatmentName(
            String treatmentName
    ) {

        this.treatmentName =
                treatmentName;
    }


    // =========================================================
    // DESCRIPTION
    // =========================================================

    public String getDescription() {

        return description;
    }

    public void setDescription(
            String description
    ) {

        this.description =
                description;
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
    // ACTIVE
    // =========================================================

    public boolean isActive() {

        return active;
    }

    public void setActive(
            boolean active
    ) {

        this.active =
                active;
    }


    // =========================================================
    // COMPATIBILITY METHODS
    // =========================================================

    public double getCost() {

        return treatmentCost;
    }

    public void setCost(
            double cost
    ) {

        this.treatmentCost =
                cost;
    }


    // =========================================================
    // TO STRING
    // =========================================================

    @Override
    public String toString() {

        return treatmentName
                + " - Rs. "
                + String.format(
                "%.2f",
                treatmentCost
        );
    }
}