package com.sunrise.dental.resource;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.service.AppointmentService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentResource {

    private final AppointmentService appointmentService;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AppointmentResource() {

        appointmentService =
                new AppointmentService();
    }

    // =========================================================
    // GET ALL APPOINTMENTS
    // URL:
    // GET http://localhost:8080/api/appointments
    // =========================================================

    @GET
    public Response getAllAppointments() {

        try {

            List<Appointment> appointments =
                    appointmentService.getAllAppointments();

            return Response
                    .ok(appointments)
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return Response
                    .status(
                            Response.Status.INTERNAL_SERVER_ERROR
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    false,

                                    "message",
                                    "Unable to load appointments."
                            )
                    )
                    .build();
        }
    }

    // =========================================================
    // SEARCH APPOINTMENT BY NUMBER
    //
    // URL:
    // GET http://localhost:8080/api/appointments/APT-2026-00125
    // =========================================================

    @GET
    @Path("/{appointmentNumber}")
    public Response getAppointment(
            @PathParam("appointmentNumber")
            String appointmentNumber
    ) {

        try {

            // -------------------------------------------------
            // VALIDATE NUMBER
            // -------------------------------------------------

            if (
                    appointmentNumber == null
                            ||
                            appointmentNumber.trim().isEmpty()
            ) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Appointment number is required."
                                )
                        )
                        .build();
            }

            String cleanNumber =
                    appointmentNumber
                            .trim()
                            .toUpperCase();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "SEARCH APPOINTMENT REQUEST"
            );

            System.out.println(
                    "Appointment Number: "
                            + cleanNumber
            );

            System.out.println(
                    "========================================"
            );

            // -------------------------------------------------
            // SEARCH DATABASE
            // -------------------------------------------------

            Appointment appointment =
                    appointmentService.searchAppointment(
                            cleanNumber
                    );

            // -------------------------------------------------
            // NOT FOUND
            // -------------------------------------------------

            if (appointment == null) {

                System.out.println(
                        "Appointment not found: "
                                + cleanNumber
                );

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Appointment not found: "
                                                + cleanNumber
                                )
                        )
                        .build();
            }

            // -------------------------------------------------
            // SUCCESS
            // -------------------------------------------------

            System.out.println(
                    "Appointment found: "
                            + appointment.getAppointmentNumber()
            );

            return Response
                    .ok(appointment)
                    .build();

        } catch (IllegalArgumentException e) {

            return Response
                    .status(
                            Response.Status.BAD_REQUEST
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    false,

                                    "message",
                                    e.getMessage()
                            )
                    )
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return Response
                    .status(
                            Response.Status.INTERNAL_SERVER_ERROR
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    false,

                                    "message",
                                    "Unable to search appointment."
                            )
                    )
                    .build();
        }
    }

    // =========================================================
    // REGISTER APPOINTMENT
    //
    // POST:
    // http://localhost:8080/api/appointments
    // =========================================================

    @POST
    public Response registerAppointment(
            Appointment appointment
    ) {

        try {

            // -------------------------------------------------
            // VALIDATE REQUEST
            // -------------------------------------------------

            if (appointment == null) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Appointment information is required."
                                )
                        )
                        .build();
            }

            // -------------------------------------------------
            // DEBUG
            // -------------------------------------------------

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "REGISTER APPOINTMENT REQUEST"
            );

            System.out.println(
                    "Appointment Number: "
                            + appointment.getAppointmentNumber()
            );

            System.out.println(
                    "Patient ID: "
                            + appointment.getPatientId()
            );

            System.out.println(
                    "Dentist ID: "
                            + appointment.getDentistId()
            );

            System.out.println(
                    "Treatment ID: "
                            + appointment.getTreatmentId()
            );

            System.out.println(
                    "Date: "
                            + appointment.getAppointmentDate()
            );

            System.out.println(
                    "Time: "
                            + appointment.getAppointmentTime()
            );

            System.out.println(
                    "========================================"
            );

            // -------------------------------------------------
            // REGISTER
            // -------------------------------------------------

            int appointmentId =
                    appointmentService.registerAppointment(
                            appointment
                    );

            // -------------------------------------------------
            // FAILURE
            // -------------------------------------------------

            if (appointmentId == -1) {

                return Response
                        .status(
                                Response.Status.INTERNAL_SERVER_ERROR
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Appointment registration failed."
                                )
                        )
                        .build();
            }

            // -------------------------------------------------
            // SUCCESS
            // -------------------------------------------------

            return Response
                    .status(
                            Response.Status.CREATED
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    true,

                                    "message",
                                    "Appointment registered successfully.",

                                    "appointmentId",
                                    appointmentId,

                                    "appointmentNumber",
                                    appointment.getAppointmentNumber()
                            )
                    )
                    .build();

        } catch (IllegalArgumentException e) {

            return Response
                    .status(
                            Response.Status.BAD_REQUEST
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    false,

                                    "message",
                                    e.getMessage()
                            )
                    )
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return Response
                    .status(
                            Response.Status.INTERNAL_SERVER_ERROR
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    false,

                                    "message",
                                    "An unexpected server error occurred."
                            )
                    )
                    .build();
        }
    }

    // =========================================================
    // UPDATE APPOINTMENT
    //
    // PUT:
    // /api/appointments/{appointmentId}
    // =========================================================

    @PUT
    @Path("/{appointmentId}")
    public Response updateAppointment(
            @PathParam("appointmentId")
            int appointmentId,

            Appointment appointment
    ) {

        try {

            if (appointmentId <= 0) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Invalid appointment ID."
                                )
                        )
                        .build();
            }

            if (appointment == null) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Appointment information is required."
                                )
                        )
                        .build();
            }

            appointment.setAppointmentId(
                    appointmentId
            );

            String message =
                    appointmentService.updateAppointment(
                            appointment
                    );

            return Response
                    .ok(
                            Map.of(
                                    "success",
                                    true,

                                    "message",
                                    message
                            )
                    )
                    .build();

        } catch (IllegalArgumentException e) {

            return Response
                    .status(
                            Response.Status.BAD_REQUEST
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    false,

                                    "message",
                                    e.getMessage()
                            )
                    )
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return Response
                    .status(
                            Response.Status.INTERNAL_SERVER_ERROR
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    false,

                                    "message",
                                    "Unable to update appointment."
                            )
                    )
                    .build();
        }
    }

    // =========================================================
    // CANCEL APPOINTMENT
    //
    // PUT:
    // /api/appointments/{appointmentId}/cancel
    // =========================================================

    @PUT
    @Path("/{appointmentId}/cancel")
    public Response cancelAppointment(
            @PathParam("appointmentId")
            int appointmentId
    ) {

        try {

            if (appointmentId <= 0) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Invalid appointment ID."
                                )
                        )
                        .build();
            }

            String message =
                    appointmentService.cancelAppointment(
                            appointmentId
                    );

            return Response
                    .ok(
                            Map.of(
                                    "success",
                                    true,

                                    "message",
                                    message
                            )
                    )
                    .build();

        } catch (IllegalArgumentException e) {

            return Response
                    .status(
                            Response.Status.BAD_REQUEST
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    false,

                                    "message",
                                    e.getMessage()
                            )
                    )
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return Response
                    .status(
                            Response.Status.INTERNAL_SERVER_ERROR
                    )
                    .entity(
                            Map.of(
                                    "success",
                                    false,

                                    "message",
                                    "Unable to cancel appointment."
                            )
                    )
                    .build();
        }
    }
}