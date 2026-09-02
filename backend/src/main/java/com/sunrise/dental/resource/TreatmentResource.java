package com.sunrise.dental.resource;

import com.sunrise.dental.model.Treatment;
import com.sunrise.dental.service.TreatmentService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/treatments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TreatmentResource {

    private final TreatmentService treatmentService;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public TreatmentResource() {

        treatmentService =
                new TreatmentService();
    }


    // =========================================================
    // GET ALL
    // =========================================================

    @GET
    public Response getTreatments() {

        try {

            List<Treatment> treatments =
                    treatmentService.getAllTreatments();

            return Response
                    .ok(treatments)
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
                                    "Unable to retrieve treatments."
                            )
                    )
                    .build();
        }
    }


    // =========================================================
    // ADD
    // =========================================================

    @POST
    public Response addTreatment(
            Treatment treatment
    ) {

        try {

            if (treatment == null) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Treatment data is required."
                                )
                        )
                        .build();
            }


            if (
                    treatment.getTreatmentName()
                            == null
                            ||
                            treatment.getTreatmentName()
                                    .trim()
                                    .isEmpty()
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
                                        "Treatment name is required."
                                )
                        )
                        .build();
            }


            if (
                    treatment.getDescription()
                            == null
                            ||
                            treatment.getDescription()
                                    .trim()
                                    .isEmpty()
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
                                        "Treatment description is required."
                                )
                        )
                        .build();
            }


            if (
                    treatment.getTreatmentCost()
                            < 0
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
                                        "Treatment cost cannot be negative."
                                )
                        )
                        .build();
            }


            if (
                    treatment.getConsultationFee()
                            < 0
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
                                        "Consultation fee cannot be negative."
                                )
                        )
                        .build();
            }


            Treatment createdTreatment =
                    treatmentService.addTreatment(
                            treatment
                    );


            return Response
                    .status(
                            Response.Status.CREATED
                    )
                    .entity(
                            createdTreatment
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
                                    "Unable to add treatment."
                            )
                    )
                    .build();
        }
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    @GET
    @Path("/{id}")
    public Response getTreatmentById(
            @PathParam("id") int id
    ) {

        try {

            Treatment treatment =
                    treatmentService.getTreatmentById(
                            id
                    );


            if (treatment == null) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Treatment not found."
                                )
                        )
                        .build();
            }


            return Response
                    .ok(treatment)
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
                                    "Unable to retrieve treatment."
                            )
                    )
                    .build();
        }
    }
}