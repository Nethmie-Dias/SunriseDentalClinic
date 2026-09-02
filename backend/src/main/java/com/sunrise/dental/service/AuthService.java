package com.sunrise.dental.service;

import com.sunrise.dental.dao.UserDAO;
import com.sunrise.dental.model.User;

public class AuthService {

    private final UserDAO userDAO;

    public AuthService() {
        userDAO = new UserDAO();
    }

    public User login(
            String username,
            String password) {

        return userDAO.findUser(
                username,
                password
        );
    }
}