package com.asapp.backend.challenge.service.api;

import com.asapp.backend.challenge.controller.responses.SendMessageResponse;
import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.resources.Content;
import com.asapp.backend.challenge.resources.MessageResource;

import java.util.List;

public interface MessageService {

    SendMessageResponse sendMessage(MessageResource<Content> message) throws InvalidUserException;

    List<MessageResource<Content>> getMessages(Integer recipient, Integer start, Integer limit);
}
