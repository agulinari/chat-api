package com.asapp.backend.challenge.service.api;

public interface TokenService {

    /**
     * Generate a JWT Token
     * @return token
     */
    String generateToken();

    /**
     * Validate JWT Token
     * @param token
     * @return  true if token is valid, otherwise false
     */
    boolean validateToken(String token);

}
