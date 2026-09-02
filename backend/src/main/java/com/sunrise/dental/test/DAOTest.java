package com.sunrise.dental.test;

import com.sunrise.dental.dao.DentistDAO;
import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.Treatment;

public class DAOTest {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("SUNRISE DENTAL DAO TEST");
        System.out.println("=================================");

        DentistDAO dentistDAO = new DentistDAO();

        System.out.println("\nDENTISTS:");

        for (Dentist dentist : dentistDAO.getAllDentists()) {

            System.out.println(
                    dentist.getDentistId()
                            + " | "
                            + dentist.getDentistName()
                            + " | "
                            + dentist.getSpecialization()
            );
        }

        TreatmentDAO treatmentDAO = new TreatmentDAO();

        System.out.println("\nTREATMENTS:");

        for (Treatment treatment :
                treatmentDAO.getAllTreatments()) {

            System.out.println(
                    treatment.getTreatmentId()
                            + " | "
                            + treatment.getTreatmentName()
                            + " | Rs. "
                            + treatment.getTreatmentCost()
                            + " | Consultation: Rs. "
                            + treatment.getConsultationFee()
            );
        }

        PatientDAO patientDAO = new PatientDAO();

        System.out.println("\nPATIENTS:");

        for (Patient patient :
                patientDAO.getAllPatients()) {

            System.out.println(
                    patient.getPatientId()
                            + " | "
                            + patient.getPatientName()
                            + " | "
                            + patient.getContactNumber()
            );
        }

        System.out.println("\n=================================");
        System.out.println("DAO TEST COMPLETED");
        System.out.println("=================================");
    }
}