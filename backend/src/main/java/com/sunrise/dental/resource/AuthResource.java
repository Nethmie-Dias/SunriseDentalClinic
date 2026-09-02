package com.sunrise.dental.resource;

import com.sunrise.dental.dao.UserDAO;
import com.sunrise.dental.model.LoginRequest;
import com.sunrise.dental.model.LoginResponse;
import com.sunrise.dental.model.User;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final UserDAO userDAO;

    public AuthResource() {
        userDAO = new UserDAO();
    }

    @POST
    public Response login(LoginRequest request) {

        try {

            System.out.println("==================================");
            System.out.println("LOGIN REQUEST RECEIVED");

            if (request == null) {

                System.out.println("Login request is NULL");

                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"Login request is required\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            String username = request.getUsername();
            String password = request.getPassword();

            System.out.println("Username received: " + username);

            if (username == null || username.trim().isEmpty()) {

                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"Username is required\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (password == null || password.trim().isEmpty()) {

                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"Password is required\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            User user = userDAO.login(
                    username.trim(),
                    password
            );

            if (user == null) {

                System.out.println("Invalid username/password");

                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"message\":\"Invalid username or password\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            LoginResponse response = new LoginResponse(
                    user.getUserId(),
                    user.getUsername(),
                    user.getRole()
            );

            System.out.println(
                    "LOGIN SUCCESS: "
                            + user.getUsername()
                            + " / "
                            + user.getRole()
            );

            System.out.println("==================================");

            return Response.ok(response)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {

            System.err.println("LOGIN SERVER ERROR");
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Internal server error during login\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}