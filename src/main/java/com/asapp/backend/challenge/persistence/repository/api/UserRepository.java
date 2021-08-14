package com.asapp.backend.challenge.persistence.repository.api;

import com.asapp.backend.challenge.persistence.entities.UserEntity;

import java.util.Optional;

public interface UserRepository {

    UserEntity createUser(String username, String password);

    Optional<UserEntity> getUser(String username, String password);

}
