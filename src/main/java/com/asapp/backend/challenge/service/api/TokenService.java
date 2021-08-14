package com.asapp.backend.challenge.service.api;

public interface TokenService {

    String generateToken();

    boolean validateToken(String token);

}
