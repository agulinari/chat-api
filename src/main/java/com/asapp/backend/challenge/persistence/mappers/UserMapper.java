package com.asapp.backend.challenge.persistence.mappers;

import com.asapp.backend.challenge.persistence.entities.UserEntity;
import com.asapp.backend.challenge.resources.UserResource;

public class UserMapper implements RepositoryMapper<UserEntity, UserResource>{

    @Override
    public UserEntity mapToEntity(final UserResource domainObject) {
        return new UserEntity(domainObject.getId(), domainObject.getUsername(), domainObject.getPassword());
    }

    @Override
    public UserResource mapToDomain(final UserEntity entityObject) {
        return new UserResource(entityObject.getId(), entityObject.getUsername(), entityObject.getPassword());
    }
}
