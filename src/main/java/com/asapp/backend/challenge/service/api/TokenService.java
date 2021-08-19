package com.asapp.backend.challenge.service.api;

import com.auth0.jwt.exceptions.JWTVerificationException;

public interface TokenService {

    /**
     * Generate a JWT Token
     * @return token
     */
    String generateToken();

    /**
     * Validate JWT Token
     * @param token JWT Token to validate
     * @throws JWTVerificationException if token is invalid
     */
    void validateToken(String token)  throws JWTVerificationException;

}
