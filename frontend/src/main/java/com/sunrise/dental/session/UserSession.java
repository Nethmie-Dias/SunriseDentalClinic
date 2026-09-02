package com.sunrise.dental.session;

public class UserSession {

    private static int userId;
    private static String username;
    private static String role;

    private UserSession() {
    }

    public static void startSession(
            int id,
            String user,
            String userRole) {

        userId = id;
        username = user;
        role = userRole;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static boolean isLoggedIn() {

        return username != null &&
                !username.isBlank();
    }

    public static void clearSession() {

        userId = 0;
        username = null;
        role = null;
    }
}