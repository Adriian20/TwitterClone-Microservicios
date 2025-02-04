package com.twitterclone.user.auth_service.mapper;

import org.springframework.stereotype.Component;

import com.twitterclone.user.auth_service.dto.LoginRequest;
import com.twitterclone.user.auth_service.dto.RegisterRequest;
import com.twitterclone.user.auth_service.model.AuthEntity;

@Component
public class AuthMapper {

    public AuthEntity toEntity(RegisterRequest registerRequest) {
        AuthEntity user = new AuthEntity();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPasswordHash(registerRequest.getPassword());
        user.setBirthday(registerRequest.getBirthday());
        return user;
    }

    public AuthEntity toEntity(LoginRequest loginRequest) {
        AuthEntity user = new AuthEntity();
        user.setEmail(loginRequest.getEmail());
        user.setPasswordHash(loginRequest.getPasswordHash());
        return user;
    }
}