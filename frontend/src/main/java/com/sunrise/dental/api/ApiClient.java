package com.sunrise.dental.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Bill;
import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.model.LoginRequest;
import com.sunrise.dental.model.LoginResponse;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.Report;
import com.sunrise.dental.model.Treatment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;

import java.util.Arrays;
import java.util.List;


/**
 * =========================================================
 * SUNRISE DENTAL CLINIC
 * API CLIENT
 * =========================================================
 *
 * This class communicates with the backend REST API.
 *
 * Modules:
 *
 * 1. Login
 * 2. Patients
 * 3. Dentists
 * 4. Treatments
 * 5. Appointments
 * 6. Billing
 * 7. Reports
 *
 * =========================================================
 */
public class ApiClient {


    // =========================================================
    // BASE URL
    // =========================================================

    private static final String BASE_URL =
            "http://localhost:8080/api/";


    // =========================================================
    // OBJECT MAPPER
    // =========================================================

    private final ObjectMapper objectMapper;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ApiClient() {

        objectMapper =
                new ObjectMapper();

        objectMapper.registerModule(
                new JavaTimeModule()
        );
    }


    // =========================================================
    // LOGIN
    // =========================================================

    public LoginResponse login(
            String username,
            String password
    ) throws IOException {

        LoginRequest request =
                new LoginRequest(
                        username,
                        password
                );

        return sendJson(
                "login",
                "POST",
                request,
                LoginResponse.class
        );
    }


    // =========================================================
    // PATIENTS - GET ALL
    // =========================================================

    public List<Patient> getPatients()
            throws IOException {

        String response =
                sendGet("patients");

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return List.of();
        }

        Patient[] patients =
                objectMapper.readValue(
                        response,
                        Patient[].class
                );

        return Arrays.asList(
                patients
        );
    }


    // =========================================================
    // PATIENTS - ADD
    // =========================================================

    public Patient addPatient(
            Patient patient
    ) throws IOException {

        return sendJson(
                "patients",
                "POST",
                patient,
                Patient.class
        );
    }


    // =========================================================
    // DENTISTS - GET ALL
    // =========================================================

    public List<Dentist> getDentists()
            throws IOException {

        String response =
                sendGet("dentists");

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return List.of();
        }

        Dentist[] dentists =
                objectMapper.readValue(
                        response,
                        Dentist[].class
                );

        return Arrays.asList(
                dentists
        );
    }


    // =========================================================
    // DENTISTS - ADD
    // =========================================================

    public Dentist addDentist(
            Dentist dentist
    ) throws IOException {

        return sendJson(
                "dentists",
                "POST",
                dentist,
                Dentist.class
        );
    }


    // =========================================================
    // TREATMENTS - GET ALL
    // =========================================================

    public List<Treatment> getTreatments()
            throws IOException {

        String response =
                sendGet("treatments");

        System.out.println(
                "Treatment API Response: "
                        + response
        );

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return List.of();
        }

        Treatment[] treatments =
                objectMapper.readValue(
                        response,
                        Treatment[].class
                );

        return Arrays.asList(
                treatments
        );
    }


    // =========================================================
    // TREATMENTS - ADD
    // =========================================================

    public Treatment addTreatment(
            Treatment treatment
    ) throws IOException {

        return sendJson(
                "treatments",
                "POST",
                treatment,
                Treatment.class
        );
    }


    // =========================================================
    // TREATMENTS - GET BY ID
    // =========================================================

    public Treatment getTreatmentById(
            int treatmentId
    ) throws IOException {

        String response =
                sendGet(
                        "treatments/"
                                + treatmentId
                );

        return objectMapper.readValue(
                response,
                Treatment.class
        );
    }


    // =========================================================
    // APPOINTMENTS - GET ALL
    // =========================================================

    public List<Appointment> getAppointments()
            throws IOException {

        String response =
                sendGet(
                        "appointments"
                );

        System.out.println(
                "Appointment API Response: "
                        + response
        );

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return List.of();
        }

        Appointment[] appointments =
                objectMapper.readValue(
                        response,
                        Appointment[].class
                );

        return Arrays.asList(
                appointments
        );
    }


    // =========================================================
    // APPOINTMENT - SEARCH
    // =========================================================

    public Appointment getAppointmentByNumber(
            String appointmentNumber
    ) throws IOException {

        if (
                appointmentNumber == null
                        ||
                        appointmentNumber.trim().isEmpty()
        ) {

            throw new IOException(
                    "Appointment number is required."
            );
        }

        String cleanNumber =
                appointmentNumber
                        .trim()
                        .toUpperCase();

        String encodedNumber =
                URLEncoder.encode(
                        cleanNumber,
                        StandardCharsets.UTF_8
                );

        String endpoint =
                "appointments/"
                        + encodedNumber;

        System.out.println();
        System.out.println(
                "========================================"
        );
        System.out.println(
                "SEARCHING APPOINTMENT"
        );
        System.out.println(
                "Appointment Number: "
                        + cleanNumber
        );
        System.out.println(
                "Endpoint: "
                        + endpoint
        );
        System.out.println(
                "========================================"
        );

        String response;

        try {

            response =
                    sendGet(
                            endpoint
                    );

        } catch (IOException ex) {

            throw new IOException(
                    ex.getMessage()
            );
        }

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return null;
        }

        JsonNode jsonNode =
                objectMapper.readTree(
                        response
                );

        if (
                jsonNode.has("success")
                        &&
                        !jsonNode
                                .get("success")
                                .asBoolean()
        ) {

            String message =
                    jsonNode.has("message")
                            ?
                            jsonNode
                                    .get("message")
                                    .asText()
                            :
                            "Appointment not found.";

            throw new IOException(
                    message
            );
        }

        return objectMapper.readValue(
                response,
                Appointment.class
        );
    }


    // =========================================================
    // REGISTER APPOINTMENT
    // =========================================================

    public String addAppointment(
            Appointment appointment
    ) throws IOException {

        if (
                appointment == null
        ) {

            throw new IOException(
                    "Appointment information is required."
            );
        }

        String response =
                sendJsonRaw(
                        "appointments",
                        "POST",
                        appointment
                );

        System.out.println(
                "Appointment Registration Response: "
                        + response
        );

        return extractMessage(
                response,
                "Appointment registered successfully."
        );
    }


    // =========================================================
    // UPDATE APPOINTMENT
    // =========================================================

    public String updateAppointment(
            Appointment appointment
    ) throws IOException {

        if (
                appointment == null
        ) {

            throw new IOException(
                    "Appointment information is required."
            );
        }

        if (
                appointment.getAppointmentId() <= 0
        ) {

            throw new IOException(
                    "Invalid appointment ID."
            );
        }

        String endpoint =
                "appointments/"
                        + appointment.getAppointmentId();

        String response =
                sendJsonRaw(
                        endpoint,
                        "PUT",
                        appointment
                );

        System.out.println(
                "Appointment Update Response: "
                        + response
        );

        return extractMessage(
                response,
                "Appointment updated successfully."
        );
    }


    // =========================================================
    // CANCEL APPOINTMENT
    // =========================================================

    public String cancelAppointment(
            int appointmentId
    ) throws IOException {

        if (
                appointmentId <= 0
        ) {

            throw new IOException(
                    "Invalid appointment ID."
            );
        }

        String endpoint =
                "appointments/"
                        + appointmentId
                        + "/cancel";

        String response =
                sendJsonRaw(
                        endpoint,
                        "PUT",
                        null
                );

        System.out.println(
                "Appointment Cancel Response: "
                        + response
        );

        return extractMessage(
                response,
                "Appointment cancelled successfully."
        );
    }


    // =========================================================
    // BILLING - CALCULATE BILL
    // =========================================================

    public Bill calculateBill(
            String appointmentNumber
    ) throws IOException {

        if (
                appointmentNumber == null
                        ||
                        appointmentNumber
                                .trim()
                                .isEmpty()
        ) {

            throw new IOException(
                    "Appointment number is required."
            );
        }

        String cleanNumber =
                appointmentNumber
                        .trim()
                        .toUpperCase();

        String encodedNumber =
                URLEncoder.encode(
                        cleanNumber,
                        StandardCharsets.UTF_8
                );

        String endpoint =
                "bills/calculate/"
                        + encodedNumber;

        System.out.println();
        System.out.println(
                "========================================"
        );
        System.out.println(
                "CALCULATING BILL"
        );
        System.out.println(
                "Appointment Number: "
                        + cleanNumber
        );
        System.out.println(
                "Endpoint: "
                        + endpoint
        );
        System.out.println(
                "========================================"
        );

        String response =
                sendJsonRaw(
                        endpoint,
                        "POST",
                        null
                );

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            throw new IOException(
                    "Server returned an empty billing response."
            );
        }

        JsonNode jsonNode =
                objectMapper.readTree(
                        response
                );

        if (
                jsonNode.has("success")
                        &&
                        !jsonNode
                                .get("success")
                                .asBoolean()
        ) {

            String message =
                    jsonNode.has("message")
                            ?
                            jsonNode
                                    .get("message")
                                    .asText()
                            :
                            "Bill could not be calculated.";

            throw new IOException(
                    message
            );
        }

        return objectMapper.readValue(
                response,
                Bill.class
        );
    }


    // =========================================================
    // BILLING - SAVE BILL
    // =========================================================

    public int saveBill(
            Bill bill
    ) throws IOException {

        if (
                bill == null
        ) {

            throw new IOException(
                    "Bill information is required."
            );
        }

        String response =
                sendJsonRaw(
                        "bills",
                        "POST",
                        bill
                );

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            throw new IOException(
                    "Server returned an empty response."
            );
        }

        JsonNode jsonNode =
                objectMapper.readTree(
                        response
                );

        if (
                jsonNode.has("success")
                        &&
                        !jsonNode
                                .get("success")
                                .asBoolean()
        ) {

            String message =
                    jsonNode.has("message")
                            ?
                            jsonNode
                                    .get("message")
                                    .asText()
                            :
                            "Bill could not be saved.";

            throw new IOException(
                    message
            );
        }

        if (
                jsonNode.has("billId")
        ) {

            return jsonNode
                    .get("billId")
                    .asInt();
        }

        throw new IOException(
                "Bill was saved but no bill ID was returned."
        );
    }


    // =========================================================
    // BILLING - GET SAVED BILL
    // =========================================================

    public Bill getBill(
            int appointmentId
    ) throws IOException {

        if (
                appointmentId <= 0
        ) {

            throw new IOException(
                    "Invalid appointment ID."
            );
        }

        String response =
                sendGet(
                        "bills/appointment/"
                                + appointmentId
                );

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return null;
        }

        JsonNode jsonNode =
                objectMapper.readTree(
                        response
                );

        if (
                jsonNode.has("success")
                        &&
                        !jsonNode
                                .get("success")
                                .asBoolean()
        ) {

            String message =
                    jsonNode.has("message")
                            ?
                            jsonNode
                                    .get("message")
                                    .asText()
                            :
                            "Bill not found.";

            throw new IOException(
                    message
            );
        }

        return objectMapper.readValue(
                response,
                Bill.class
        );
    }


    // =========================================================
    // REPORTS - APPOINTMENT REPORT
    // =========================================================

    public List<Report> getAppointmentReports()
            throws IOException {

        String response =
                sendGet(
                        "reports/appointments"
                );

        System.out.println();
        System.out.println(
                "========================================"
        );
        System.out.println(
                "APPOINTMENT REPORT API"
        );
        System.out.println(
                "Endpoint: reports/appointments"
        );
        System.out.println(
                "Response: "
                        + response
        );
        System.out.println(
                "========================================"
        );


        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return List.of();
        }


        Report[] reports =
                objectMapper.readValue(
                        response,
                        Report[].class
                );


        return Arrays.asList(
                reports
        );
    }


    // =========================================================
    // REPORTS - PATIENT REPORT
    // =========================================================

    public List<Report> getPatientReports()
            throws IOException {

        String response =
                sendGet(
                        "reports/patients"
                );

        System.out.println();
        System.out.println(
                "========================================"
        );
        System.out.println(
                "PATIENT REPORT API"
        );
        System.out.println(
                "Endpoint: reports/patients"
        );
        System.out.println(
                "Response: "
                        + response
        );
        System.out.println(
                "========================================"
        );


        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return List.of();
        }


        Report[] reports =
                objectMapper.readValue(
                        response,
                        Report[].class
                );


        return Arrays.asList(
                reports
        );
    }


    // =========================================================
    // REPORTS - BILLING REPORT
    // =========================================================

    public List<Report> getBillingReports()
            throws IOException {

        String response =
                sendGet(
                        "reports/billing"
                );

        System.out.println();
        System.out.println(
                "========================================"
        );
        System.out.println(
                "BILLING REPORT API"
        );
        System.out.println(
                "Endpoint: reports/billing"
        );
        System.out.println(
                "Response: "
                        + response
        );
        System.out.println(
                "========================================"
        );


        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return List.of();
        }


        Report[] reports =
                objectMapper.readValue(
                        response,
                        Report[].class
                );


        return Arrays.asList(
                reports
        );
    }


    // =========================================================
    // GENERIC GET
    // =========================================================

    private String sendGet(
            String endpoint
    ) throws IOException {

        HttpURLConnection connection =
                null;

        try {

            URL url =
                    new URL(
                            BASE_URL
                                    + endpoint
                    );

            connection =
                    (HttpURLConnection)
                            url.openConnection();

            connection.setRequestMethod(
                    "GET"
            );

            connection.setRequestProperty(
                    "Accept",
                    "application/json"
            );

            connection.setConnectTimeout(
                    5000
            );

            connection.setReadTimeout(
                    10000
            );


            int status =
                    connection.getResponseCode();


            InputStream inputStream;


            if (
                    status >= 200
                            &&
                            status < 300
            ) {

                inputStream =
                        connection.getInputStream();

            } else {

                inputStream =
                        connection.getErrorStream();


                if (
                        inputStream == null
                ) {

                    throw new IOException(
                            "Server returned HTTP "
                                    + status
                    );
                }
            }


            String response =
                    readResponse(
                            inputStream
                    );


            System.out.println(
                    "GET "
                            + endpoint
                            + " -> HTTP "
                            + status
            );


            System.out.println(
                    "Response: "
                            + response
            );


            if (
                    status < 200
                            ||
                            status >= 300
            ) {

                String message =
                        extractServerErrorMessage(
                                response
                        );

                throw new IOException(
                        message
                );
            }


            return response;

        } finally {

            if (
                    connection != null
            ) {

                connection.disconnect();
            }
        }
    }


    // =========================================================
    // GENERIC JSON REQUEST
    // =========================================================

    private <T> T sendJson(
            String endpoint,
            String method,
            Object requestObject,
            Class<T> responseClass
    ) throws IOException {

        String response =
                sendJsonRaw(
                        endpoint,
                        method,
                        requestObject
                );


        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            throw new IOException(
                    "Server returned an empty response."
            );
        }


        return objectMapper.readValue(
                response,
                responseClass
        );
    }


    // =========================================================
    // SEND JSON
    // =========================================================

    private String sendJsonRaw(
            String endpoint,
            String method,
            Object requestObject
    ) throws IOException {

        HttpURLConnection connection =
                null;


        try {

            URL url =
                    new URL(
                            BASE_URL
                                    + endpoint
                    );


            connection =
                    (HttpURLConnection)
                            url.openConnection();


            connection.setRequestMethod(
                    method
            );


            connection.setRequestProperty(
                    "Content-Type",
                    "application/json; charset=UTF-8"
            );


            connection.setRequestProperty(
                    "Accept",
                    "application/json"
            );


            connection.setConnectTimeout(
                    5000
            );


            connection.setReadTimeout(
                    10000
            );


            connection.setDoOutput(
                    true
            );


            String json;


            if (
                    requestObject == null
            ) {

                json = "";

            } else {

                json =
                        objectMapper.writeValueAsString(
                                requestObject
                        );
            }


            byte[] bytes =
                    json.getBytes(
                            StandardCharsets.UTF_8
                    );


            connection.setFixedLengthStreamingMode(
                    bytes.length
            );


            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "SENDING REQUEST"
            );

            System.out.println(
                    "METHOD: "
                            + method
            );

            System.out.println(
                    "URL: "
                            + BASE_URL
                            + endpoint
            );

            System.out.println(
                    "JSON: "
                            + json
            );

            System.out.println(
                    "========================================"
            );


            try (
                    OutputStream outputStream =
                            connection.getOutputStream()
            ) {

                if (
                        bytes.length > 0
                ) {

                    outputStream.write(
                            bytes
                    );
                }

                outputStream.flush();
            }


            int status =
                    connection.getResponseCode();


            InputStream inputStream;


            if (
                    status >= 200
                            &&
                            status < 300
            ) {

                inputStream =
                        connection.getInputStream();

            } else {

                inputStream =
                        connection.getErrorStream();


                if (
                        inputStream == null
                ) {

                    throw new IOException(
                            "Server returned HTTP "
                                    + status
                    );
                }
            }


            String response =
                    readResponse(
                            inputStream
                    );


            System.out.println(
                    "HTTP STATUS: "
                            + status
            );


            System.out.println(
                    "SERVER RESPONSE: "
                            + response
            );


            if (
                    status < 200
                            ||
                            status >= 300
            ) {

                throw new IOException(
                        extractServerErrorMessage(
                                response
                        )
                );
            }


            return response;

        } finally {

            if (
                    connection != null
            ) {

                connection.disconnect();
            }
        }
    }


    // =========================================================
    // EXTRACT MESSAGE
    // =========================================================

    private String extractMessage(
            String response,
            String defaultMessage
    ) throws IOException {

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            throw new IOException(
                    "Server returned an empty response."
            );
        }


        JsonNode jsonNode =
                objectMapper.readTree(
                        response
                );


        if (
                jsonNode.has("success")
        ) {

            boolean success =
                    jsonNode
                            .get("success")
                            .asBoolean();


            String message =
                    jsonNode.has("message")
                            ?
                            jsonNode
                                    .get("message")
                                    .asText()
                            :
                            defaultMessage;


            if (
                    !success
            ) {

                throw new IOException(
                        message
                );
            }


            return message;
        }


        if (
                jsonNode.has("message")
        ) {

            return jsonNode
                    .get("message")
                    .asText();
        }


        return defaultMessage;
    }


    // =========================================================
    // SERVER ERROR
    // =========================================================

    private String extractServerErrorMessage(
            String response
    ) {

        if (
                response == null
                        ||
                        response.trim().isEmpty()
        ) {

            return "Server returned an error.";
        }


        try {

            JsonNode jsonNode =
                    objectMapper.readTree(
                            response
                    );


            if (
                    jsonNode.has("message")
            ) {

                return jsonNode
                        .get("message")
                        .asText();
            }


            if (
                    jsonNode.has("error")
            ) {

                return jsonNode
                        .get("error")
                        .asText();
            }

        } catch (
                Exception ignored
        ) {

        }


        return response;
    }


    // =========================================================
    // READ RESPONSE
    // =========================================================

    private String readResponse(
            InputStream inputStream
    ) throws IOException {

        if (
                inputStream == null
        ) {

            return "";
        }


        StringBuilder result =
                new StringBuilder();


        try (
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        inputStream,
                                        StandardCharsets.UTF_8
                                )
                        )
        ) {

            String line;


            while (
                    (line = reader.readLine())
                            != null
            ) {

                result.append(
                        line
                );
            }
        }


        return result.toString();
    }
}