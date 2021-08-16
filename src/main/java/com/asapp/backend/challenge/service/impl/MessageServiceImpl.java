package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.controller.responses.SendMessageResponse;
import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.persistence.entities.ImageMessageEntity;
import com.asapp.backend.challenge.persistence.entities.MessageEntity;
import com.asapp.backend.challenge.persistence.entities.VideoMessageEntity;
import com.asapp.backend.challenge.persistence.mappers.ImageContentMapper;
import com.asapp.backend.challenge.persistence.mappers.MessageMapper;
import com.asapp.backend.challenge.persistence.mappers.VideoContentMapper;
import com.asapp.backend.challenge.persistence.repository.api.MessageRepository;
import com.asapp.backend.challenge.persistence.repository.impl.MessageRepositoryImpl;
import com.asapp.backend.challenge.resources.*;
import com.asapp.backend.challenge.service.api.MessageService;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final ImageContentMapper imageContentMapper;
    private final VideoContentMapper videoContentMapper;

    public MessageServiceImpl() {
        this.messageRepository = new MessageRepositoryImpl();
        this.messageMapper = new MessageMapper();
        this.imageContentMapper = new ImageContentMapper();
        this.videoContentMapper = new VideoContentMapper();
    }

    @Override
    public SendMessageResponse sendMessage(MessageResource<Content> message) throws InvalidUserException, SQLException {

        MessageEntity messageEntity = messageMapper.mapToEntity(message);
        Content content = message.getContent();
        Integer id;
        if (content instanceof TextContent) {
            id = this.messageRepository.saveMessage(messageEntity);
        } else if (content instanceof ImageContent) {
            ImageContent imageContent = (ImageContent) content;
            ImageMessageEntity imageMessageEntity = imageContentMapper.mapToEntity(imageContent);
            id = this.messageRepository.saveImageMessage(messageEntity, imageMessageEntity);
        } else if (content instanceof VideoContent) {
            VideoContent videoContent = (VideoContent) content;
            VideoMessageEntity videoMessageEntity = videoContentMapper.mapToEntity(videoContent);
            id = this.messageRepository.saveVideoMessage(messageEntity, videoMessageEntity);
        } else {
            throw new UnsupportedOperationException();
        }
        log.info("Message sent from sender: {} to recipient: {}", messageEntity.getSender(), messageEntity.getRecipient());
        return new SendMessageResponse(id, messageEntity.getTimestamp());
    }

    @Override
    public List<MessageResource<Content>> getMessages(Integer recipient, Integer start, Integer limit) throws SQLException {

        List<MessageEntity> messageEntities = this.messageRepository.getMessages(recipient, start, limit);
        List<MessageResource<Content>> messages = new ArrayList<>();
        for (MessageEntity messageEntity : messageEntities) {
            MessageResource<Content> messageResource = messageMapper.mapToDomain(messageEntity);
            if (messageEntity.getType().equals("image")) {
                ImageMessageEntity imageEntity = this.messageRepository.getImageMessage(messageEntity.getId()).get();
                ImageContent imageContent = this.imageContentMapper.mapToDomain(imageEntity);
                messageResource.setContent(imageContent);
            }
            if (messageEntity.getType().equals("video")) {
                VideoMessageEntity videoEntity = this.messageRepository.getVideoMessage(messageEntity.getId()).get();
                VideoContent videoContent = this.videoContentMapper.mapToDomain(videoEntity);
                messageResource.setContent(videoContent);
            }
            messages.add(messageResource);
        }
        return messages;
    }

}
