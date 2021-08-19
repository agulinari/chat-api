package com.asapp.backend.challenge.persistence.repository.api;

import com.asapp.backend.challenge.exceptions.InvalidMessageException;
import com.asapp.backend.challenge.persistence.entities.ImageMessageEntity;
import com.asapp.backend.challenge.persistence.entities.MessageEntity;
import com.asapp.backend.challenge.persistence.entities.VideoMessageEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MessageRepository {

    List<MessageEntity> getMessages(Integer recipient, Integer start, Integer limit) throws SQLException;

    Optional<ImageMessageEntity> getImageMessage(Integer messageId) throws SQLException;

    Optional<VideoMessageEntity> getVideoMessage(Integer messageId) throws SQLException;

    Integer saveMessage(MessageEntity messageEntity) throws InvalidMessageException, SQLException;

    Integer saveImageMessage(MessageEntity messageEntity, ImageMessageEntity imageMessageEntity) throws SQLException, InvalidMessageException;

    Integer saveVideoMessage(MessageEntity messageEntity, VideoMessageEntity videoMessageEntity) throws SQLException, InvalidMessageException;

}
