package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.persistence.entities.UserEntity;
import com.asapp.backend.challenge.persistence.mappers.UserMapper;
import com.asapp.backend.challenge.persistence.repository.api.UserRepository;
import com.asapp.backend.challenge.persistence.repository.impl.UserRepositoryImpl;
import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.resources.UserResource;
import com.asapp.backend.challenge.service.api.TokenService;
import com.asapp.backend.challenge.service.api.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl() {
        this.tokenService = new TokenServiceImpl();
        this.userRepository = new UserRepositoryImpl();
        this.userMapper = new UserMapper();
    }

    @Override
    public UserResource createUser(String username, String password) {
        UserEntity userEntity = userRepository.createUser(username, password);
        return userMapper.mapToDomain(userEntity);
    }

    @Override
    public LoginResource login(String username, String password) {
        Optional<UserEntity> oUserEntity = userRepository.getUser(username, password);
        if (!oUserEntity.isPresent()) {

        }
        UserEntity userEntity = oUserEntity.get();
        String token = tokenService.generateToken();

        LoginResource loginResource = new LoginResource(userEntity.getId(), token);
        return loginResource;
    }
}