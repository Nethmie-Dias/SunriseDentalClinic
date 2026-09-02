package com.sunrise.dental.service;

import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.model.Treatment;

import java.util.List;

public class TreatmentService {

    private final TreatmentDAO treatmentDAO;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public TreatmentService() {

        treatmentDAO =
                new TreatmentDAO();
    }


    // =========================================================
    // GET ALL
    // =========================================================

    public List<Treatment> getAllTreatments() {

        return treatmentDAO.getAllTreatments();
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    public Treatment getTreatmentById(
            int treatmentId
    ) {

        if (treatmentId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid treatment ID."
            );
        }

        return treatmentDAO.getTreatmentById(
                treatmentId
        );
    }


    // =========================================================
    // ADD
    // =========================================================

    public Treatment addTreatment(
            Treatment treatment
    ) {

        if (treatment == null) {

            throw new IllegalArgumentException(
                    "Treatment is required."
            );
        }


        if (
                treatment.getTreatmentName()
                        == null
                        ||
                        treatment.getTreatmentName()
                                .trim()
                                .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "Treatment name is required."
            );
        }


        if (
                treatment.getDescription()
                        == null
                        ||
                        treatment.getDescription()
                                .trim()
                                .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "Treatment description is required."
            );
        }


        if (
                treatment.getTreatmentCost()
                        < 0
        ) {

            throw new IllegalArgumentException(
                    "Treatment cost cannot be negative."
            );
        }


        if (
                treatment.getConsultationFee()
                        < 0
        ) {

            throw new IllegalArgumentException(
                    "Consultation fee cannot be negative."
            );
        }


        treatment.setActive(true);


        return treatmentDAO.addTreatment(
                treatment
        );
    }
}