package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.service.api.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
public class TokenServiceImpl implements TokenService {

    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public TokenServiceImpl() {
        this.algorithm = Algorithm.HMAC256("secret");
        this.verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
    }

    @Override
    public String generateToken() {
        try {
            final Instant now = Instant.now();
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withIssuedAt(Date.from(now))
                    .withExpiresAt(Date.from(now.plus(15, ChronoUnit.MINUTES)))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            log.error("Error creating token", exception);
            throw exception;
        }
    }

    @Override
    public boolean validateToken(String token) {
       try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            log.error("Invalid token", exception);
            return false;
        }
    }
}
