package com.asapp.backend.challenge.filter;

import com.asapp.backend.challenge.service.api.TokenService;
import com.asapp.backend.challenge.service.impl.TokenServiceImpl;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

public class TokenValidatorFilter {

    private static final TokenService tokenService = new TokenServiceImpl();

    public static Filter validateUser = (Request req, Response resp) -> {
        // TODO: validate token
        if (!tokenService.validateToken("token")) {
            Spark.halt(401, "Invalid token");
        }
    };
}
