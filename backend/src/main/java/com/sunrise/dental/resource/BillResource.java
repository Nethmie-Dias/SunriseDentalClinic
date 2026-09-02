package com.sunrise.dental.resource;

import com.sunrise.dental.model.Bill;
import com.sunrise.dental.service.BillingService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/bills")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillResource {

    private final BillingService billingService;

    public BillResource() {
        this.billingService =
                new BillingService();
    }

    @POST
    @Path("/calculate/{appointmentNumber}")
    public Response calculateBill(
            @PathParam("appointmentNumber")
            String appointmentNumber) {

        try {

            Bill bill =
                    billingService.calculateBill(
                            appointmentNumber
                    );

            return Response.ok(bill).build();

        } catch (IllegalArgumentException e) {

            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    Map.of(
                            "success", false,
                            "message",
                            e.getMessage()
                    )
            ).build();
        }
    }

    @POST
    public Response saveBill(Bill bill) {

        try {

            int billId =
                    billingService.saveBill(bill);

            if (billId == -1) {

                return Response.status(
                        Response.Status.INTERNAL_SERVER_ERROR
                ).entity(
                        Map.of(
                                "success", false,
                                "message",
                                "Bill could not be saved."
                        )
                ).build();
            }

            return Response.status(
                    Response.Status.CREATED
            ).entity(
                    Map.of(
                            "success", true,
                            "message",
                            "Bill saved successfully.",
                            "billId",
                            billId
                    )
            ).build();

        } catch (IllegalArgumentException e) {

            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    Map.of(
                            "success", false,
                            "message",
                            e.getMessage()
                    )
            ).build();
        }
    }

    @GET
    @Path("/appointment/{appointmentId}")
    public Response getBill(
            @PathParam("appointmentId")
            int appointmentId) {

        Bill bill =
                billingService.getBill(
                        appointmentId
                );

        if (bill == null) {

            return Response.status(
                    Response.Status.NOT_FOUND
            ).entity(
                    Map.of(
                            "success", false,
                            "message",
                            "Bill not found."
                    )
            ).build();
        }

        return Response.ok(bill).build();
    }
}