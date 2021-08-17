package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.service.api.TokenService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TokenServiceTest {

    private TokenService tokenService;

    @Before
    public void setUp() {
        tokenService = new TokenServiceImpl();
    }

    @Test
    public void testValidateCorrectToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwPCuzs";
        boolean result = tokenService.validateToken(token);

        assertTrue(result);
    }

    @Test
    public void testValidateIncorrectToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NigJ9.eyJpc3MiOiJhdXRoMCJ9";
        boolean result = tokenService.validateToken(token);

        assertFalse(result);
    }
}
