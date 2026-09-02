package com.sunrise.dental.resource;

import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.service.DentistService;

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

@Path("/dentists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DentistResource {

    private final DentistService dentistService;

    public DentistResource() {

        dentistService =
                new DentistService();
    }

    // =========================================================
    // GET ALL DENTISTS
    // =========================================================

    @GET
    public Response getDentists() {

        try {

            System.out.println(
                    "GET /api/dentists"
            );

            List<Dentist> dentists =
                    dentistService.getAllDentists();

            System.out.println(
                    "Dentists found: "
                            + dentists.size()
            );

            return Response
                    .ok(dentists)
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
                                    e.getMessage() != null
                                            ? e.getMessage()
                                            : "Unable to retrieve dentists."
                            )
                    )
                    .build();
        }
    }

    // =========================================================
    // ADD DENTIST
    // =========================================================

    @POST
    public Response addDentist(
            Dentist dentist
    ) {

        try {

            System.out.println(
                    "=================================="
            );

            System.out.println(
                    "POST /api/dentists"
            );

            System.out.println(
                    "Dentist received: "
                            + dentist
            );

            System.out.println(
                    "=================================="
            );

            if (dentist == null) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Dentist data is required."
                                )
                        )
                        .build();
            }

            Dentist createdDentist =
                    dentistService.addDentist(
                            dentist
                    );

            System.out.println(
                    "Dentist added successfully."
            );

            System.out.println(
                    "ID: "
                            + createdDentist.getDentistId()
            );

            return Response
                    .status(
                            Response.Status.CREATED
                    )
                    .entity(
                            createdDentist
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

            System.err.println(
                    "ERROR ADDING DENTIST"
            );

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
                                    e.getMessage() != null
                                            ? e.getMessage()
                                            : "Unable to add dentist."
                            )
                    )
                    .build();
        }
    }

    // =========================================================
    // GET DENTIST BY ID
    // =========================================================

    @GET
    @Path("/{id}")
    public Response getDentistById(
            @PathParam("id") int id
    ) {

        try {

            Dentist dentist =
                    dentistService.getDentistById(
                            id
                    );

            if (dentist == null) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Dentist not found."
                                )
                        )
                        .build();
            }

            return Response
                    .ok(dentist)
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
                                    e.getMessage() != null
                                            ? e.getMessage()
                                            : "Unable to retrieve dentist."
                            )
                    )
                    .build();
        }
    }
}