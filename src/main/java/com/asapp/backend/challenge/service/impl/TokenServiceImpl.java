package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.service.api.TokenService;
import com.asapp.backend.challenge.utils.PropertiesUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
public class TokenServiceImpl implements TokenService {

    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final String secret = PropertiesUtil.properties.getProperty("token.secret");
    private final String expiration = PropertiesUtil.properties.getProperty("token.expiration-minutes");
    private final String issuer = PropertiesUtil.properties.getProperty("token.issuer");

    public TokenServiceImpl() {
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    @Override
    public String generateToken() {
        try {
            final Instant now = Instant.now();
            String token = JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(Date.from(now))
                    .withExpiresAt(Date.from(now.plus(Integer.valueOf(expiration), ChronoUnit.MINUTES)))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            log.error("Error creating token", exception);
            throw exception;
        }
    }

    @Override
    public void validateToken(String token) throws JWTVerificationException {
        verifier.verify(token);
    }
}
