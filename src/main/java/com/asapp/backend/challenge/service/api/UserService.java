package com.asapp.backend.challenge.service.api;

import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.resources.UserResource;

public interface UserService {

    UserResource createUser(String username, String password);

    LoginResource login(String username, String password) throws InvalidUserException;

}
