package com.asapp.backend.challenge.persistence.repository.api;

import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.persistence.entities.ImageMessageEntity;
import com.asapp.backend.challenge.persistence.entities.MessageEntity;
import com.asapp.backend.challenge.persistence.entities.VideoMessageEntity;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {

    List<MessageEntity> getMessages(Integer recipient, Integer start, Integer limit);

    Optional<ImageMessageEntity> getImageMessage(Integer messageId);

    Optional<VideoMessageEntity> getVideoMessage(Integer messageId);

    Integer saveMessage(MessageEntity messageEntity) throws InvalidUserException;

    Integer saveImageMessage(MessageEntity messageEntity, ImageMessageEntity imageMessageEntity);

    Integer saveVideoMessage(MessageEntity messageEntity, VideoMessageEntity videoMessageEntity);

}
