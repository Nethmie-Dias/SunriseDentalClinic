package com.sunrise.dental;

import com.sunrise.dental.model.LoginRequest;
import com.sunrise.dental.resource.AuthResource;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthResourceTest {

    // ---------------------------------------------------------
    // TEST 1: Null login request
    // ---------------------------------------------------------

    @Test
    void login_shouldRejectNullRequest() {

        AuthResource authResource = new AuthResource();

        Response response = authResource.login(null);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
                response.getStatus()
        );

        assertTrue(
                response.getEntity().toString().contains("Login request is required")
        );

        response.close();
    }


    // ---------------------------------------------------------
    // TEST 2: Empty username
    // ---------------------------------------------------------

    @Test
    void login_shouldRejectEmptyUsername() {

        AuthResource authResource = new AuthResource();

        LoginRequest request =
                new LoginRequest("", "password123");

        Response response =
                authResource.login(request);

        assertEquals(
                Response.Status.BAD_REQUEST.getStatusCode(),
                response.getStatus()
        );

        assertTrue(
                response.getEntity().toString()
                        .contains("Username is required")
        );

        response.close();
    }


    // ---------------------------------------------------------
    // TEST 3: Null username
    // ---------------------------------------------------------

    @Test
    void login_shouldRejectNullUsername() {

        AuthResource authResource = new AuthResource();

        LoginRequest request =
                new LoginRequest(null, "password123");

        Response response =
                authResource.login(request);

        assertEquals(
                Response.Status.BAD_REQUEST.getStatusCode(),
                response.getStatus()
        );

        assertTrue(
                response.getEntity().toString()
                        .contains("Username is required")
        );

        response.close();
    }


    // ---------------------------------------------------------
    // TEST 4: Empty password
    // ---------------------------------------------------------

    @Test
    void login_shouldRejectEmptyPassword() {

        AuthResource authResource = new AuthResource();

        LoginRequest request =
                new LoginRequest("admin", "");

        Response response =
                authResource.login(request);

        assertEquals(
                Response.Status.BAD_REQUEST.getStatusCode(),
                response.getStatus()
        );

        assertTrue(
                response.getEntity().toString()
                        .contains("Password is required")
        );

        response.close();
    }


    // ---------------------------------------------------------
    // TEST 5: Null password
    // ---------------------------------------------------------

    @Test
    void login_shouldRejectNullPassword() {

        AuthResource authResource = new AuthResource();

        LoginRequest request =
                new LoginRequest("admin", null);

        Response response =
                authResource.login(request);

        assertEquals(
                Response.Status.BAD_REQUEST.getStatusCode(),
                response.getStatus()
        );

        assertTrue(
                response.getEntity().toString()
                        .contains("Password is required")
        );

        response.close();
    }


    // ---------------------------------------------------------
    // TEST 6: Both fields empty
    // ---------------------------------------------------------

    @Test
    void login_shouldRejectEmptyUsernameAndPassword() {

        AuthResource authResource = new AuthResource();

        LoginRequest request =
                new LoginRequest("", "");

        Response response =
                authResource.login(request);

        assertEquals(
                Response.Status.BAD_REQUEST.getStatusCode(),
                response.getStatus()
        );

        assertTrue(
                response.getEntity().toString()
                        .contains("Username is required")
        );

        response.close();
    }


    // ---------------------------------------------------------
    // TEST 7: Username containing spaces
    // ---------------------------------------------------------

    @Test
    void login_shouldTrimUsername() {

        AuthResource authResource = new AuthResource();

        LoginRequest request =
                new LoginRequest("   ", "password123");

        Response response =
                authResource.login(request);

        assertEquals(
                Response.Status.BAD_REQUEST.getStatusCode(),
                response.getStatus()
        );

        assertTrue(
                response.getEntity().toString()
                        .contains("Username is required")
        );

        response.close();
    }
}