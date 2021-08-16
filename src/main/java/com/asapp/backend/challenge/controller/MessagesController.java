package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.responses.SendMessageResponse;
import com.asapp.backend.challenge.resources.Content;
import com.asapp.backend.challenge.resources.MessageResource;
import com.asapp.backend.challenge.service.api.MessageService;
import com.asapp.backend.challenge.service.impl.MessageServiceImpl;
import com.asapp.backend.challenge.utils.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class MessagesController {

    private static final MessageService messageService = new MessageServiceImpl();

    public static Route sendMessage = (Request req, Response rep) -> {
        MessageResource<Content> message = JSONUtil.jsonToData(req.body(), new TypeReference<MessageResource<Content>>() {});
        SendMessageResponse response = messageService.sendMessage(message);
        return JSONUtil.dataToJson(response);
    };

    public static Route getMessages = (Request req, Response rep) -> {
        Integer recipient = Integer.valueOf(req.queryParams("recipient"));
        Integer start = Integer.valueOf(req.queryParams("start"));
        Integer limit = Integer.valueOf(req.queryParams("limit"));
        List<MessageResource<Content>> messages = messageService.getMessages(recipient, start, limit);

        return JSONUtil.dataToJson(messages);
    };

}
