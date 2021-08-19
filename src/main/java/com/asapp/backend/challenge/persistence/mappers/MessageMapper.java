package com.asapp.backend.challenge.persistence.mappers;

import com.asapp.backend.challenge.persistence.entities.MessageEntity;
import com.asapp.backend.challenge.resources.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MessageMapper implements RepositoryMapper<MessageEntity, MessageResource<Content>> {

    @Override
    public MessageEntity mapToEntity(final MessageResource<Content> domainObject) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setRecipient(domainObject.getRecipient());
        messageEntity.setSender(domainObject.getSender());
        messageEntity.setTimestamp(LocalDateTime.now(ZoneOffset.UTC));
        Content content = domainObject.getContent();
        messageEntity.setType(content.getType());
        messageEntity.setText(content.getText());
        return messageEntity;
    }

    @Override
    public MessageResource<Content> mapToDomain(final MessageEntity entityObject) {
        MessageResource<Content> messageResource = new MessageResource<>();
        messageResource.setId(entityObject.getId());
        messageResource.setRecipient(entityObject.getRecipient());
        messageResource.setSender(entityObject.getSender());
        messageResource.setTimestamp(entityObject.getTimestamp());
        //Text is the default content
        TextContent content = new TextContent();
        content.setType(entityObject.getType());
        content.setText(entityObject.getText());
        messageResource.setContent(content);
        return messageResource;
    }
}
