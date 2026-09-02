
package com.sunrise.dental.resource;

import com.sunrise.dental.model.Patient;
import com.sunrise.dental.service.PatientService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {

    private final PatientService patientService;

    public PatientResource() {

        patientService =
                new PatientService();
    }

    // =========================================================
    // GET ALL PATIENTS
    // =========================================================

    @GET
    public Response getPatients() {

        try {

            List<Patient> patients =
                    patientService.getAllPatients();

            System.out.println(
                    "GET /patients"
            );

            System.out.println(
                    "Patients found: "
                            + patients.size()
            );

            return Response
                    .ok(patients)
                    .build();

        } catch (Exception e) {

            System.err.println(
                    "ERROR GETTING PATIENTS"
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
                                    "Unable to retrieve patients."
                            )
                    )
                    .build();
        }
    }

    // =========================================================
    // ADD PATIENT
    // =========================================================

    @POST
    public Response addPatient(
            Patient patient
    ) {

        try {

            System.out.println(
                    "POST /patients"
            );

            if (patient == null) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                Map.of(
                                        "success",
                                        false,

                                        "message",
                                        "Patient data is required."
                                )
                        )
                        .build();
            }

            Patient createdPatient =
                    patientService.addPatient(
                            patient
                    );

            System.out.println(
                    "Patient added: "
                            + createdPatient.getPatientId()
            );

            return Response
                    .status(
                            Response.Status.CREATED
                    )
                    .entity(createdPatient)
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
                    "ERROR ADDING PATIENT"
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
                                    "Unable to add patient."
                            )
                    )
                    .build();
        }
    }
}