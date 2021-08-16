package com.asapp.backend.challenge.persistence.mappers;

import com.asapp.backend.challenge.persistence.entities.VideoMessageEntity;
import com.asapp.backend.challenge.resources.VideoContent;

public class VideoContentMapper implements RepositoryMapper<VideoMessageEntity, VideoContent> {

    @Override
    public VideoMessageEntity mapToEntity(final VideoContent domainObject) {
        VideoMessageEntity entity = new VideoMessageEntity();
        entity.setUrl(domainObject.getUrl());
        entity.setSource(domainObject.getSource());
        return entity;
    }

    @Override
    public VideoContent mapToDomain(final VideoMessageEntity entityObject) {
        VideoContent domainObject = new VideoContent();
        domainObject.setType("video");
        domainObject.setUrl(entityObject.getUrl());
        domainObject.setSource(entityObject.getSource());
        return domainObject;
    }
}
