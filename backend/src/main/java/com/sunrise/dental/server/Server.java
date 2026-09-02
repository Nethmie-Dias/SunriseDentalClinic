package com.sunrise.dental;

import com.sunrise.dental.resource.AppointmentResource;
import com.sunrise.dental.resource.AuthResource;
import com.sunrise.dental.resource.BillResource;
import com.sunrise.dental.resource.DentistResource;
import com.sunrise.dental.resource.PatientResource;
import com.sunrise.dental.resource.TreatmentResource;
import com.sunrise.dental.resource.ReportResource;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import java.io.IOException;
import java.net.URI;

public class Server {

    private static final String BASE_URI =
            "http://localhost:8080/api/";


    public static void main(String[] args)
            throws IOException {

        /*
         * ==========================================
         * CREATE JERSEY CONFIGURATION
         * ==========================================
         */

        ResourceConfig config =
                new ResourceConfig();


        /*
         * ==========================================
         * REGISTER JACKSON JSON SUPPORT
         * ==========================================
         */

        config.register(
                JacksonFeature.class
        );


        /*
         * ==========================================
         * REGISTER REST RESOURCES
         * ==========================================
         */

        config.register(
                AuthResource.class
        );

        config.register(
                PatientResource.class
        );

        config.register(
                DentistResource.class
        );

        config.register(
                TreatmentResource.class
        );

        config.register(
                AppointmentResource.class
        );

        config.register(
                BillResource.class
        );

        /*
         * ==========================================
         * REPORT RESOURCE
         * ==========================================
         */

        config.register(
                ReportResource.class
        );


        /*
         * ==========================================
         * START SERVER
         * ==========================================
         */

        HttpServer server =
                GrizzlyHttpServerFactory.createHttpServer(
                        URI.create(BASE_URI),
                        config
                );


        /*
         * ==========================================
         * SERVER INFORMATION
         * ==========================================
         */

        System.out.println();

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "       SUNRISE DENTAL CLINIC API"
        );

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "Server started successfully."
        );

        System.out.println(
                "Base URL: " + BASE_URI
        );

        System.out.println();


        /*
         * ==========================================
         * AVAILABLE ENDPOINTS
         * ==========================================
         */

        System.out.println(
                "Available endpoints:"
        );

        System.out.println();

        System.out.println(
                "POST /api/login"
        );

        System.out.println(
                "GET  /api/patients"
        );

        System.out.println(
                "POST /api/patients"
        );

        System.out.println(
                "GET  /api/dentists"
        );

        System.out.println(
                "GET  /api/treatments"
        );

        System.out.println(
                "GET  /api/appointments"
        );

        System.out.println(
                "POST /api/appointments"
        );

        System.out.println(
                "GET  /api/appointments/{number}"
        );

        System.out.println(
                "POST /api/bills/calculate/{number}"
        );

        /*
         * ==========================================
         * REPORT ENDPOINTS
         * ==========================================
         */

        System.out.println();

        System.out.println(
                "REPORT ENDPOINTS:"
        );

        System.out.println(
                "GET  /api/reports/appointments"
        );

        System.out.println(
                "GET  /api/reports/patients"
        );

        System.out.println(
                "GET  /api/reports/billing"
        );


        System.out.println();

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "Press ENTER to stop the server."
        );


        /*
         * ==========================================
         * KEEP SERVER RUNNING
         * ==========================================
         */

        System.in.read();


        /*
         * ==========================================
         * STOP SERVER SAFELY
         * ==========================================
         */

        server.shutdownNow();


        System.out.println(
                "Server stopped."
        );
    }
}