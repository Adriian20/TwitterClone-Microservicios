package com.twitterclone.user.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.twitterclone.user.dto.UserDTO;
import com.twitterclone.user.model.UserEntity;
import com.twitterclone.user.repository.UserRepository;
import com.twitterclone.user.util.JwtUtil;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(@Validated UserDTO userDTO) {
        log.info("Registering in user: {}", userDTO);
        return Optional.ofNullable(userDTO)
                .map(UserDTO::convertToEntity)
                .map(userFound -> {
                    String token = JwtUtil.generateToken(userFound.getEmail());
                    userFound.setToken(token);
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    String encodedPassword = encoder.encode(userFound.getPassword());
                    userFound.setPassword(encodedPassword);
                    return userFound;
                })
                .map(userRepository::save)
                .orElseThrow(() -> new IllegalArgumentException("Datos de usuario inválidos"));
    }

    public UserEntity loginUser(@Validated UserDTO userDTO) {
        log.info("Logging in user: {}", userDTO);
        return Optional.ofNullable(userDTO)
                .map(UserDTO::convertToEntity)
                .map(user -> userRepository.findByEmail(user.getEmail()))
                .filter(userToken -> userToken.getToken() == null || userToken.getToken().isEmpty())
                .filter(userFound -> {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    return encoder.matches(userDTO.getPassword(), userFound.getPassword());
                })
                .map(userFound -> {
                    String token = JwtUtil.generateToken(userFound.getEmail());
                    userFound.setToken(token);
                    return userFound;
                })
                .map(userRepository::save)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));
    }

    public UserEntity logoutUser(@Validated UserDTO userDTO) {
        log.info("Logging out user: {}", userDTO);
        return Optional.ofNullable(userDTO)
                .map(UserDTO::convertToEntity)
                .map(user -> userRepository.findByEmail(user.getEmail()))
                .filter(userFound -> userFound.getToken().equals(userDTO.getToken()))
                .map(userFound -> {
                    userFound.setToken(null);
                    return userFound;
                })
                .map(userRepository::save)
                .orElse(null);
    }
}
