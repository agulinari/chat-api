package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.controller.requests.MessageRequest;
import com.asapp.backend.challenge.controller.responses.SendMessageResponse;
import com.asapp.backend.challenge.persistence.mappers.TextMessageMapper;
import com.asapp.backend.challenge.persistence.repository.api.MessageRepository;
import com.asapp.backend.challenge.persistence.repository.impl.MessageRepositoryImpl;
import com.asapp.backend.challenge.resources.Content;
import com.asapp.backend.challenge.resources.MessageResource;
import com.asapp.backend.challenge.service.api.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final TextMessageMapper messageMapper;

    public MessageServiceImpl() {
        this.messageRepository = new MessageRepositoryImpl();
        this.messageMapper = new TextMessageMapper();
    }

    @Override
    public SendMessageResponse sendMessage(MessageRequest message) {
        return null;
    }

    @Override
    public List<MessageResource<Content>> getMessages(Integer recipient, Integer start, Integer limit) {
        return null;
    }

}
