package com.asapp.backend.challenge.service.api;

import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.resources.UserResource;

import java.sql.SQLException;

public interface UserService {

    /**
     * Create a user in the system.
     * @param username Username
     * @param password Password
     * @return Created user
     * @throws InvalidUserException
     */
    UserResource createUser(String username, String password) throws InvalidUserException;

    /**
     * Log in as an existing user.
     * @param username Username
     * @param password Password
     * @return User ID and JWT token
     * @throws InvalidUserException
     * @throws SQLException
     */
    LoginResource login(String username, String password) throws InvalidUserException, SQLException;

}
