package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.requests.UserRequest;
import com.asapp.backend.challenge.resources.UserResource;
import com.asapp.backend.challenge.service.api.UserService;
import com.asapp.backend.challenge.service.impl.UserServiceImpl;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class UsersController {

    private static final UserService userService = new UserServiceImpl();

    public static Route createUser = (Request req, Response resp) -> {
        UserRequest createUserRequest = JSONUtil.jsonToData(req.body(), UserRequest.class);

        UserResource userResource = userService.createUser(createUserRequest.getUsername(), createUserRequest.getPassword());
        return JSONUtil.dataToJson(userResource);
    };
}
