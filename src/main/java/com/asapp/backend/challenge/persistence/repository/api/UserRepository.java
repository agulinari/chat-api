package com.asapp.backend.challenge.persistence.repository.api;

import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.persistence.entities.UserEntity;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository {

    UserEntity createUser(String username, String password) throws InvalidUserException;

    Optional<UserEntity> getUser(String username, String password) throws SQLException;

}
