package com.asapp.backend.challenge.filter;

import com.asapp.backend.challenge.controller.responses.ErrorResponse;
import com.asapp.backend.challenge.service.api.TokenService;
import com.asapp.backend.challenge.service.impl.TokenServiceImpl;
import com.asapp.backend.challenge.utils.HttpCodes;
import com.asapp.backend.challenge.utils.JSONUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

public class TokenValidatorFilter {

    private static final TokenService tokenService = new TokenServiceImpl();

    public static Filter validateUser = (Request req, Response resp) -> {
        resp.header("Content-Type", "application/json");

        String token = req.headers("Authorization");

        try {
            tokenService.validateToken(token);
        } catch (JWTVerificationException e) {
            Spark.halt(HttpCodes.HTTP_UNAUTHORIZED, JSONUtil.dataToJson(new ErrorResponse("Invalid token")));
        }
    };
}
