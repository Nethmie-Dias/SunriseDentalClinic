
package com.sunrise.dental.model;

public class Dentist {

    private int dentistId;
    private String dentistName;
    private String specialization;
    private String contactNumber;
    private String email;

    // =========================================================
    // DEFAULT CONSTRUCTOR
    // Required by Jackson
    // =========================================================

    public Dentist() {
    }

    // =========================================================
    // CONSTRUCTOR FOR ADDING DENTIST
    // =========================================================

    public Dentist(
            String dentistName,
            String specialization,
            String contactNumber,
            String email
    ) {
        this.dentistName = dentistName;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // =========================================================
    // CONSTRUCTOR WITH ID
    // =========================================================

    public Dentist(
            int dentistId,
            String dentistName,
            String specialization,
            String contactNumber,
            String email
    ) {
        this.dentistId = dentistId;
        this.dentistName = dentistName;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // =========================================================
    // GETTER / SETTER - DENTIST ID
    // =========================================================

    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
    }

    // =========================================================
    // GETTER / SETTER - DENTIST NAME
    // =========================================================

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    // =========================================================
    // GETTER / SETTER - SPECIALIZATION
    // =========================================================

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // =========================================================
    // GETTER / SETTER - CONTACT NUMBER
    // =========================================================

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // =========================================================
    // GETTER / SETTER - EMAIL
    // =========================================================

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // =========================================================
    // TO STRING
    // =========================================================

    @Override
    public String toString() {
        return dentistName + " - " + specialization;
    }
}

