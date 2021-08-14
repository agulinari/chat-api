package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.requests.MessageRequest;
import com.asapp.backend.challenge.resources.Content;
import com.asapp.backend.challenge.resources.MessageResource;
import com.asapp.backend.challenge.utils.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Collections;

public class MessagesController {

    public static Route sendMessage = (Request req, Response rep) -> {
        MessageRequest<Content> message = JSONUtil.jsonToData(req.body(), new TypeReference<MessageRequest<Content>>() {});
        return JSONUtil.dataToJson(message);
    };

    public static Route getMessages = (Request req, Response rep) -> {
        // TODO: Retrieve list of Messages
        return JSONUtil.dataToJson(Collections.singletonList(new MessageResource()));
    };

}
