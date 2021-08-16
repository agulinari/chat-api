package com.asapp.backend.challenge.service.api;

import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.resources.UserResource;

import java.sql.SQLException;

public interface UserService {

    /**
     * Create a user in the system.
     * @param username
     * @param password
     * @return
     * @throws InvalidUserException
     */
    UserResource createUser(String username, String password) throws InvalidUserException;

    /**
     * Log in as an existing user.
     * @param username
     * @param password
     * @return
     * @throws InvalidUserException
     * @throws SQLException
     */
    LoginResource login(String username, String password) throws InvalidUserException, SQLException;

}
