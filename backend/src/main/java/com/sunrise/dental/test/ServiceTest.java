package com.sunrise.dental.test;

import com.sunrise.dental.model.Bill;
import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.model.Treatment;
import com.sunrise.dental.service.BillingService;
import com.sunrise.dental.service.DentistService;
import com.sunrise.dental.service.TreatmentService;

public class ServiceTest {

    public static void main(String[] args) {

        System.out.println(
                "================================="
        );

        System.out.println(
                "SUNRISE DENTAL SERVICE TEST"
        );

        System.out.println(
                "================================="
        );

        DentistService dentistService =
                new DentistService();

        System.out.println("\nDENTISTS:");

        for (Dentist dentist :
                dentistService.getAllDentists()) {

            System.out.println(
                    dentist.getDentistId()
                            + " | "
                            + dentist.getDentistName()
                            + " | "
                            + dentist.getSpecialization()
            );
        }

        TreatmentService treatmentService =
                new TreatmentService();

        System.out.println("\nTREATMENTS:");

        for (Treatment treatment :
                treatmentService.getAllTreatments()) {

            System.out.println(
                    treatment.getTreatmentId()
                            + " | "
                            + treatment.getTreatmentName()
                            + " | Rs. "
                            + treatment.getTreatmentCost()
                            + " | Consultation Rs. "
                            + treatment.getConsultationFee()
            );
        }

        System.out.println(
                "\n================================="
        );

        System.out.println(
                "SERVICE TEST COMPLETED"
        );

        System.out.println(
                "================================="
        );
    }
}