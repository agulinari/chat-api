package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.responses.ErrorResponse;
import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.exceptions.RequiredFieldException;
import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.resources.UserResource;
import com.asapp.backend.challenge.service.api.UserService;
import com.asapp.backend.challenge.service.impl.UserServiceImpl;
import com.asapp.backend.challenge.utils.HttpCodes;
import com.asapp.backend.challenge.utils.JSONUtil;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Response;
import spark.Route;

@Slf4j
public class AuthController {

    private static final UserService userService = new UserServiceImpl();


    public static Route login = (Request req, Response resp) -> {

        try {

            UserResource userRequest = JSONUtil.jsonToData(req.body(), UserResource.class);
            validateRequest(userRequest);
            LoginResource loginResource = userService.login(userRequest.getUsername(), userRequest.getPassword());
            return JSONUtil.dataToJson(loginResource);

        } catch (JsonParseException jpe) {

            resp.status(HttpCodes.HTTP_BAD_REQUEST);
            return JSONUtil.dataToJson(new ErrorResponse("Malformed request"));

        } catch (InvalidUserException iue) {

            resp.status(HttpCodes.HTTP_UNAUTHORIZED);
            return JSONUtil.dataToJson(new ErrorResponse("Invalid credentials"));

        } catch (RequiredFieldException rfe) {

            resp.status(HttpCodes.HTTP_BAD_REQUEST);
            return JSONUtil.dataToJson(new ErrorResponse(rfe.getMessage()));

        } catch (Exception e ) {

            resp.status(HttpCodes.HTTP_INTERNAL_SERVER_EXCEPTION);
            return JSONUtil.dataToJson(new ErrorResponse(e.getMessage()));

        }

    };

    private static void validateRequest(UserResource userRequest) throws RequiredFieldException {
        if (userRequest.getUsername() == null || userRequest.getUsername().trim().isEmpty()) {
            throw new RequiredFieldException("username");
        }

        if (userRequest.getPassword() == null || userRequest.getPassword().trim().isEmpty()) {
            throw new RequiredFieldException("password");
        }
    }

}
