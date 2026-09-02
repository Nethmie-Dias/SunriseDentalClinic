package com.sunrise.dental.resource;

import com.sunrise.dental.service.ReportService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/reports")
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {

    private final ReportService reportService;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ReportResource() {

        reportService =
                new ReportService();
    }


    // =========================================================
    // APPOINTMENT REPORT
    // =========================================================

    @GET
    @Path("/appointments")
    public Response getAppointmentReport() {

        try {

            return Response
                    .ok(
                            reportService
                                    .getAppointmentReport()
                    )
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return Response
                    .serverError()
                    .entity(
                            "{\"success\":false,"
                                    + "\"message\":\"Unable to load appointment report.\"}"
                    )
                    .build();
        }
    }


    // =========================================================
    // PATIENT REPORT
    // =========================================================

    @GET
    @Path("/patients")
    public Response getPatientReport() {

        try {

            return Response
                    .ok(
                            reportService
                                    .getPatientReport()
                    )
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return Response
                    .serverError()
                    .entity(
                            "{\"success\":false,"
                                    + "\"message\":\"Unable to load patient report.\"}"
                    )
                    .build();
        }
    }


    // =========================================================
    // BILLING REPORT
    // =========================================================

    @GET
    @Path("/billing")
    public Response getBillingReport() {

        try {

            return Response
                    .ok(
                            reportService
                                    .getBillingReport()
                    )
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return Response
                    .serverError()
                    .entity(
                            "{\"success\":false,"
                                    + "\"message\":\"Unable to load billing report.\"}"
                    )
                    .build();
        }
    }
}