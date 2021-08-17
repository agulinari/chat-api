package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.persistence.entities.UserEntity;
import com.asapp.backend.challenge.persistence.repository.api.UserRepository;
import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.resources.UserResource;
import com.asapp.backend.challenge.service.api.TokenService;
import com.asapp.backend.challenge.service.api.UserService;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private TokenService tokenService;

    @Before
    public void setUp() {
        this.userRepository = mock(UserRepository.class);
        this.tokenService = mock(TokenService.class);
        userService = new UserServiceImpl(tokenService, userRepository);
    }

    @Test
    public void testCreateUserOk() throws InvalidUserException {

        String username = "username01";
        String password = "A3d232f3f3";

        UserEntity userEntity = new UserEntity(1, username, password);
        when(userRepository.createUser(username, password)).thenReturn(userEntity);

        UserResource userResource = this.userService.createUser(username, password);

        assertEquals(userResource.getId().intValue(), 1);
        assertEquals(userResource.getUsername(), username);
        assertEquals(userResource.getPassword(), password);

    }

    @Test
    public void testLoginOk() throws SQLException, InvalidUserException {

        String username = "username01";
        String password = "A3d232f3f3";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwPCuzs";

        UserEntity userEntity = new UserEntity(1, username, password);
        when(userRepository.getUser(username, password)).thenReturn(Optional.of(userEntity));

        when(tokenService.generateToken()).thenReturn(token);
        LoginResource loginResource = this.userService.login(username, password);

        assertEquals(loginResource.getId().intValue(), 1);
        assertEquals(loginResource.getToken(), token);

    }

    @Test(expected = InvalidUserException.class)
    public void testUserAlreadyExists() throws SQLException, InvalidUserException {

        String username = "username02";
        String password = "A3d232f3f3";

        when(userRepository.createUser(username, password)).thenThrow(new InvalidUserException());

        this.userService.createUser(username, password);

    }

    @Test(expected = InvalidUserException.class)
    public void testInvalidCredentials() throws SQLException, InvalidUserException {

        String username = "wrongUser";
        String password = "1111111111";

        when(userRepository.getUser(username, password)).thenReturn(Optional.empty());

        this.userService.login(username, password);

    }
}
