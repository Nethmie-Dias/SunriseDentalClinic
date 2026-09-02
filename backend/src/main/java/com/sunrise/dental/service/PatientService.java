package com.sunrise.dental.service;

import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.model.Patient;

import java.util.List;

public class PatientService {

    private final PatientDAO patientDAO;

    public PatientService() {

        patientDAO =
                new PatientDAO();
    }

    // =========================================================
    // ADD PATIENT
    // =========================================================

    public Patient addPatient(
            Patient patient
    ) {

        if (patient == null) {

            throw new IllegalArgumentException(
                    "Patient data is required."
            );
        }

        if (patient.getPatientName() == null ||
                patient.getPatientName()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Patient name is required."
            );
        }

        if (patient.getContactNumber() == null ||
                patient.getContactNumber()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Contact number is required."
            );
        }

        if (patient.getAddress() == null ||
                patient.getAddress()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Address is required."
            );
        }

        return patientDAO.addPatient(
                patient
        );
    }

    // =========================================================
    // GET ALL PATIENTS
    // =========================================================

    public List<Patient> getAllPatients() {

        return patientDAO.getAllPatients();
    }

    // =========================================================
    // GET PATIENT BY ID
    // =========================================================

    public Patient getPatientById(
            int patientId
    ) {

        if (patientId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid patient ID."
            );
        }

        return patientDAO.getPatientById(
                patientId
        );
    }
}