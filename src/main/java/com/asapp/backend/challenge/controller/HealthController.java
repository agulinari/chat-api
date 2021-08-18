package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.resources.HealthResource;
import com.asapp.backend.challenge.utils.DatabaseUtil;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class HealthController {

    public static Route check = (Request req, Response rep) -> {
        rep.header("Content-Type", "application/json");
        if (DatabaseUtil.test()) {
            return JSONUtil.dataToJson(new HealthResource("ok"));
        } else {
           return JSONUtil.dataToJson(new HealthResource("down"));
        }
    };
}
