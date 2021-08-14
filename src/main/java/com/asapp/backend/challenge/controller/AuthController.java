package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.requests.UserRequest;
import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.service.api.UserService;
import com.asapp.backend.challenge.service.impl.UserServiceImpl;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthController {

    private static final UserService userService = new UserServiceImpl();

    public static Route login = (Request req, Response resp) -> {
        UserRequest userRequest = JSONUtil.jsonToData(req.body(), UserRequest.class);

        LoginResource loginResource = userService.login(userRequest.getUsername(), userRequest.getPassword());
        return JSONUtil.dataToJson(loginResource);

    };

}
