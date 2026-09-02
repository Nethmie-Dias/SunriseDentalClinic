package com.sunrise.dental.service;

import com.sunrise.dental.dao.ReportDAO;

import java.util.List;
import java.util.Map;

public class ReportService {

    private final ReportDAO reportDAO;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ReportService() {

        reportDAO =
                new ReportDAO();
    }


    // =========================================================
    // APPOINTMENT REPORT
    // =========================================================

    public List<Map<String, Object>>
    getAppointmentReport() {

        return reportDAO
                .getAppointmentReport();
    }


    // =========================================================
    // PATIENT REPORT
    // =========================================================

    public List<Map<String, Object>>
    getPatientReport() {

        return reportDAO
                .getPatientReport();
    }


    // =========================================================
    // BILLING REPORT
    // =========================================================

    public List<Map<String, Object>>
    getBillingReport() {

        return reportDAO
                .getBillingReport();
    }
}