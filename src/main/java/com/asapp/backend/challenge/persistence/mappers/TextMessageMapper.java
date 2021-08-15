package com.asapp.backend.challenge.persistence.mappers;

import com.asapp.backend.challenge.persistence.entities.MessageEntity;
import com.asapp.backend.challenge.resources.*;

import java.time.LocalDateTime;

public class TextMessageMapper implements RepositoryMapper<MessageEntity, MessageResource<TextContent>> {

    @Override
    public MessageEntity mapToEntity(final MessageResource<TextContent> domainObject) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setRecipient(domainObject.getRecipient());
        messageEntity.setSender(domainObject.getSender());
        messageEntity.setTimestamp(LocalDateTime.now());
        TextContent content = domainObject.getContent();
        messageEntity.setType(content.getType());
        messageEntity.setText(content.getText());
        return messageEntity;
    }

    @Override
    public MessageResource<TextContent> mapToDomain(final MessageEntity entityObject) {
        return new MessageResource<>();
    }
}
