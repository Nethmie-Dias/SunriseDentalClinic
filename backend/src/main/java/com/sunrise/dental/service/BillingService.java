package com.sunrise.dental.service;

import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.dao.BillDAO;
import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Bill;
import com.sunrise.dental.model.Treatment;

import java.time.LocalDateTime;

public class BillingService {

    private final BillDAO billDAO;
    private final AppointmentDAO appointmentDAO;
    private final TreatmentDAO treatmentDAO;

    public BillingService() {

        this.billDAO = new BillDAO();
        this.appointmentDAO = new AppointmentDAO();
        this.treatmentDAO = new TreatmentDAO();
    }

    public Bill calculateBill(
            String appointmentNumber) {

        if (appointmentNumber == null ||
                appointmentNumber.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Appointment number is required."
            );
        }

        Appointment appointment =
                appointmentDAO.getAppointmentByNumber(
                        appointmentNumber.trim()
                );

        if (appointment == null) {

            throw new IllegalArgumentException(
                    "Appointment not found."
            );
        }

        Treatment treatment =
                treatmentDAO.getTreatmentById(
                        appointment.getTreatmentId()
                );

        if (treatment == null) {

            throw new IllegalArgumentException(
                    "Treatment information not found."
            );
        }

        double treatmentCost =
                treatment.getTreatmentCost();

        double consultationFee =
                treatment.getConsultationFee();

        double total =
                treatmentCost + consultationFee;

        return new Bill(
                0,
                appointment.getAppointmentId(),
                treatmentCost,
                consultationFee,
                total,
                LocalDateTime.now()
        );
    }

    public int saveBill(Bill bill) {

        if (bill == null) {

            throw new IllegalArgumentException(
                    "Bill information is required."
            );
        }

        if (bill.getAppointmentId() <= 0) {

            throw new IllegalArgumentException(
                    "Invalid appointment."
            );
        }

        if (bill.getTotalAmount() <= 0) {

            throw new IllegalArgumentException(
                    "Bill amount must be greater than zero."
            );
        }

        return billDAO.createBill(bill);
    }

    public Bill getBill(int appointmentId) {

        if (appointmentId <= 0) {
            return null;
        }

        return billDAO.getBillByAppointmentId(
                appointmentId
        );
    }
}