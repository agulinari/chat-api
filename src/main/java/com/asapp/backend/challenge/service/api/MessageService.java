package com.asapp.backend.challenge.service.api;

import com.asapp.backend.challenge.controller.requests.MessageRequest;
import com.asapp.backend.challenge.controller.responses.SendMessageResponse;
import com.asapp.backend.challenge.resources.Content;
import com.asapp.backend.challenge.resources.MessageResource;
import java.util.List;

public interface MessageService {

    SendMessageResponse sendMessage(MessageRequest message);

    List<MessageResource<Content>> getMessages(Integer recipient, Integer start, Integer limit);
}
