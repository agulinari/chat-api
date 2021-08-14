package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.service.api.TokenService;

public class TokenServiceImpl implements TokenService {

    public TokenServiceImpl() {
    }

    @Override
    public String generateToken() {
        return "token";
    }

    @Override
    public boolean validateToken(String token) {
        return true;
    }
}
