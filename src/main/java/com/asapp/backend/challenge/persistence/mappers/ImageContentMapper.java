package com.asapp.backend.challenge.persistence.mappers;

import com.asapp.backend.challenge.persistence.entities.ImageMessageEntity;
import com.asapp.backend.challenge.resources.ImageContent;

public class ImageContentMapper implements RepositoryMapper<ImageMessageEntity, ImageContent> {

    @Override
    public ImageMessageEntity mapToEntity(final ImageContent domainObject) {
        ImageMessageEntity entity = new ImageMessageEntity();
        entity.setUrl(domainObject.getUrl());
        entity.setHeight(domainObject.getHeight());
        entity.setWidth(domainObject.getWidth());
        return entity;
    }

    @Override
    public ImageContent mapToDomain(final ImageMessageEntity entityObject) {
        ImageContent domainObject = new ImageContent();
        domainObject.setType("image");
        domainObject.setUrl(entityObject.getUrl());
        domainObject.setHeight(entityObject.getHeight());
        domainObject.setWidth(entityObject.getWidth());
        return domainObject;
    }

}
