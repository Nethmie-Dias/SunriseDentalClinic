package com.sunrise.dental;

import com.sunrise.dental.model.Appointment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    @Test
    void appointmentConstructor_shouldStoreCorrectValues() {

        Appointment appointment = new Appointment(
                1,
                "APT001",
                10,
                5,
                3,
                "John Silva",
                "Colombo",
                "0771234567",
                "Dr. Perera",
                "Dental Cleaning",
                LocalDate.of(2026, 9, 10),
                LocalTime.of(10, 30),
                "Scheduled",
                "Regular check-up"
        );

        assertEquals(1, appointment.getAppointmentId());
        assertEquals("APT001", appointment.getAppointmentNumber());
        assertEquals(10, appointment.getPatientId());
        assertEquals(5, appointment.getDentistId());
        assertEquals(3, appointment.getTreatmentId());
        assertEquals("John Silva", appointment.getPatientName());
        assertEquals("Colombo", appointment.getAddress());
        assertEquals("0771234567", appointment.getContactNumber());
        assertEquals("Dr. Perera", appointment.getDentistName());
        assertEquals("Dental Cleaning", appointment.getTreatmentName());
        assertEquals(
                LocalDate.of(2026, 9, 10),
                appointment.getAppointmentDate()
        );
        assertEquals(
                LocalTime.of(10, 30),
                appointment.getAppointmentTime()
        );
        assertEquals("Scheduled", appointment.getStatus());
        assertEquals("Regular check-up", appointment.getNotes());
    }


    @Test
    void appointmentSetters_shouldUpdateValues() {

        Appointment appointment = new Appointment();

        appointment.setAppointmentId(2);
        appointment.setAppointmentNumber("APT002");
        appointment.setPatientName("Jane Silva");
        appointment.setAddress("Kandy");
        appointment.setContactNumber("0712345678");
        appointment.setDentistName("Dr. Fernando");
        appointment.setTreatmentName("Root Canal");
        appointment.setAppointmentDate(
                LocalDate.of(2026, 9, 15)
        );
        appointment.setAppointmentTime(
                LocalTime.of(14, 00)
        );
        appointment.setStatus("Scheduled");
        appointment.setNotes("Follow-up visit");

        assertEquals(2, appointment.getAppointmentId());
        assertEquals("APT002", appointment.getAppointmentNumber());
        assertEquals("Jane Silva", appointment.getPatientName());
        assertEquals("Kandy", appointment.getAddress());
        assertEquals("0712345678", appointment.getContactNumber());
        assertEquals("Dr. Fernando", appointment.getDentistName());
        assertEquals("Root Canal", appointment.getTreatmentName());
        assertEquals(
                LocalDate.of(2026, 9, 15),
                appointment.getAppointmentDate()
        );
        assertEquals(
                LocalTime.of(14, 00),
                appointment.getAppointmentTime()
        );
        assertEquals("Scheduled", appointment.getStatus());
        assertEquals("Follow-up visit", appointment.getNotes());
    }


    @Test
    void appointmentToString_shouldReturnUsefulInformation() {

        Appointment appointment = new Appointment();

        appointment.setAppointmentNumber("APT003");
        appointment.setPatientName("David Perera");
        appointment.setAppointmentDate(
                LocalDate.of(2026, 9, 20)
        );
        appointment.setAppointmentTime(
                LocalTime.of(11, 30)
        );

        String result = appointment.toString();

        assertTrue(result.contains("APT003"));
        assertTrue(result.contains("David Perera"));
        assertTrue(result.contains("2026-09-20"));
        assertTrue(result.contains("11:30"));
    }
}