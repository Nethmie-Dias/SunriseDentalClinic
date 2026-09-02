package com.sunrise.dental.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Treatment {

    // =========================================================
    // FIELDS
    // =========================================================

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

    }


    // =========================================================
    // CONSTRUCTOR - 3 PARAMETERS
    // Used by older TreatmentFrame code
    // =========================================================

    public Treatment(
            String treatmentName,
            String description,
            double treatmentCost
    ) {

        this.treatmentName = treatmentName;

        this.description = description;

        this.treatmentCost = treatmentCost;

        this.consultationFee = 0.0;

        this.active = true;
    }


    // =========================================================
    // CONSTRUCTOR - 5 PARAMETERS
    // THIS FIXES YOUR CURRENT ERROR
    // =========================================================

    public Treatment(
            String treatmentName,
            String description,
            double treatmentCost,
            double consultationFee,
            boolean active
    ) {

        this.treatmentName = treatmentName;

        this.description = description;

        this.treatmentCost = treatmentCost;

        this.consultationFee = consultationFee;

        this.active = active;
    }


    // =========================================================
    // CONSTRUCTOR - 6 PARAMETERS
    // Used when all database fields are available
    // =========================================================

    public Treatment(
            int treatmentId,
            String treatmentName,
            String description,
            double treatmentCost,
            double consultationFee,
            boolean active
    ) {

        this.treatmentId = treatmentId;

        this.treatmentName = treatmentName;

        this.description = description;

        this.treatmentCost = treatmentCost;

        this.consultationFee = consultationFee;

        this.active = active;
    }


    // =========================================================
    // GET TREATMENT ID
    // =========================================================

    public int getTreatmentId() {

        return treatmentId;
    }


    // =========================================================
    // SET TREATMENT ID
    // =========================================================

    public void setTreatmentId(
            int treatmentId
    ) {

        this.treatmentId = treatmentId;
    }


    // =========================================================
    // GET TREATMENT NAME
    // =========================================================

    public String getTreatmentName() {

        return treatmentName;
    }


    // =========================================================
    // SET TREATMENT NAME
    // =========================================================

    public void setTreatmentName(
            String treatmentName
    ) {

        this.treatmentName = treatmentName;
    }


    // =========================================================
    // GET DESCRIPTION
    // =========================================================

    public String getDescription() {

        return description;
    }


    // =========================================================
    // SET DESCRIPTION
    // =========================================================

    public void setDescription(
            String description
    ) {

        this.description = description;
    }


    // =========================================================
    // GET TREATMENT COST
    // =========================================================

    public double getTreatmentCost() {

        return treatmentCost;
    }


    // =========================================================
    // SET TREATMENT COST
    // =========================================================

    public void setTreatmentCost(
            double treatmentCost
    ) {

        this.treatmentCost = treatmentCost;
    }


    // =========================================================
    // GET CONSULTATION FEE
    // =========================================================

    public double getConsultationFee() {

        return consultationFee;
    }


    // =========================================================
    // SET CONSULTATION FEE
    // =========================================================

    public void setConsultationFee(
            double consultationFee
    ) {

        this.consultationFee = consultationFee;
    }


    // =========================================================
    // GET ACTIVE
    // =========================================================

    public boolean isActive() {

        return active;
    }


    // =========================================================
    // SET ACTIVE
    // =========================================================

    public void setActive(
            boolean active
    ) {

        this.active = active;
    }


    // =========================================================
    // COMPATIBILITY METHOD
    //
    // Your backend currently returns:
    //
    // "cost": 50000.0
    //
    // This prevents Jackson from failing.
    // =========================================================

    @JsonProperty("cost")
    public void setCostFromBackend(
            double cost
    ) {

        /*
         * The database already provides treatmentCost.
         *
         * If treatmentCost is empty/zero and the backend
         * sends cost, use cost as the treatment cost.
         */

        if (this.treatmentCost == 0.0) {

            this.treatmentCost = cost;
        }
    }


    // =========================================================
    // COMPATIBILITY GETTER
    //
    // Allows existing frontend code to use:
    //
    // treatment.getCost()
    // =========================================================

    @JsonProperty("cost")
    public double getCost() {

        return treatmentCost;
    }


    // =========================================================
    // TO STRING
    // =========================================================

    @Override
    public String toString() {

        return "Treatment{" +
                "treatmentId=" + treatmentId +
                ", treatmentName='" + treatmentName + '\'' +
                ", description='" + description + '\'' +
                ", treatmentCost=" + treatmentCost +
                ", consultationFee=" + consultationFee +
                ", active=" + active +
                '}';
    }
}

