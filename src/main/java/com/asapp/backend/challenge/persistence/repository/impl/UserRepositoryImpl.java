package com.asapp.backend.challenge.persistence.repository.impl;

import com.asapp.backend.challenge.persistence.entities.UserEntity;
import com.asapp.backend.challenge.persistence.repository.api.UserRepository;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserEntity createUser(String username, String password) {
        return new UserEntity(1, username, password);
    }

    @Override
    public Optional<UserEntity> getUser(String username, String password) {
        return Optional.of(new UserEntity(1, username, password));
    }

}
