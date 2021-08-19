package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.service.api.TokenService;
import com.asapp.backend.challenge.utils.PropertiesUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TokenServiceTest {

    private TokenService tokenService;

    @Before
    public void setUp() throws IOException {
        PropertiesUtil.loadProperties();
        tokenService = new TokenServiceImpl();
    }

    @Test(expected = Test.None.class)
    public void testValidateCorrectToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwPCuzs";
        tokenService.validateToken(token);

    }

    @Test(expected = JWTVerificationException.class)
    public void testValidateIncorrectToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NigJ9.eyJpc3MiOiJhdXRoMCJ9";
        tokenService.validateToken(token);
    }
}
