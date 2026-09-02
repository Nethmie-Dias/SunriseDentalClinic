package com.sunrise.dental.service;

import com.sunrise.dental.dao.DentistDAO;
import com.sunrise.dental.model.Dentist;

import java.util.List;

public class DentistService {

    private final DentistDAO dentistDAO;

    public DentistService() {

        dentistDAO =
                new DentistDAO();
    }

    // =========================================================
    // ADD DENTIST
    // =========================================================

    public Dentist addDentist(
            Dentist dentist
    ) {

        if (dentist == null) {

            throw new IllegalArgumentException(
                    "Dentist data is required."
            );
        }

        if (
                dentist.getDentistName() == null
                        ||
                        dentist.getDentistName()
                                .trim()
                                .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "Dentist name is required."
            );
        }

        if (
                dentist.getSpecialization() == null
                        ||
                        dentist.getSpecialization()
                                .trim()
                                .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "Specialization is required."
            );
        }

        if (
                dentist.getContactNumber() == null
                        ||
                        dentist.getContactNumber()
                                .trim()
                                .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "Contact number is required."
            );
        }

        if (
                dentist.getEmail() == null
                        ||
                        dentist.getEmail()
                                .trim()
                                .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "Email is required."
            );
        }

        return dentistDAO.addDentist(
                dentist
        );
    }

    // =========================================================
    // GET ALL DENTISTS
    // =========================================================

    public List<Dentist> getAllDentists() {

        return dentistDAO.getAllDentists();
    }

    // =========================================================
    // GET DENTIST BY ID
    // =========================================================

    public Dentist getDentistById(
            int dentistId
    ) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist ID."
            );
        }

        return dentistDAO.getDentistById(
                dentistId
        );
    }
}