package com.sunrise.dental;

import com.sunrise.dental.dao.UserDAO;
import com.sunrise.dental.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    // ---------------------------------------------------------
    // TEST 1: Invalid username and password
    // ---------------------------------------------------------

    @Test
    void findUser_shouldReturnNullForInvalidCredentials() {

        UserDAO userDAO = new UserDAO();

        User result = userDAO.findUser(
                "invalid_test_user_999",
                "invalid_test_password_999"
        );

        assertNull(result);
    }


    // ---------------------------------------------------------
    // TEST 2: Invalid password
    // ---------------------------------------------------------

    @Test
    void login_shouldReturnNullForInvalidPassword() {

        UserDAO userDAO = new UserDAO();

        User result = userDAO.login(
                "invalid_test_user_999",
                "wrong_password_999"
        );

        assertNull(result);
    }


    // ---------------------------------------------------------
    // TEST 3: Non-existing username
    // ---------------------------------------------------------

    @Test
    void login_shouldReturnNullForNonExistingUsername() {

        UserDAO userDAO = new UserDAO();

        User result = userDAO.login(
                "user_that_does_not_exist_999",
                "password123"
        );

        assertNull(result);
    }
}