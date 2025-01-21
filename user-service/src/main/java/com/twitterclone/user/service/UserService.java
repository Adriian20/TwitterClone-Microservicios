package com.twitterclone.user.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitterclone.user.dto.UserDTO;
import com.twitterclone.user.model.UserEntity;
import com.twitterclone.user.repository.UserRepository;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(UserDTO userDTO) {
        log.info("Registering in user: {}", userDTO);
        return Optional.ofNullable(userDTO)
                .map(UserDTO::convertToEntity)
                .map(userRepository::save)
                .orElse(null);
    }

    public UserEntity loginUser(UserDTO userDTO) {
        log.info("Logging in user: {}", userDTO);
        return Optional.ofNullable(userDTO)
                .map(UserDTO::convertToEntity)
                .map(user -> userRepository.findByEmail(user.getEmail()))
                .filter(userFound -> userFound.getPassword().equals(userDTO.getPassword()))
                .orElse(null);
    }
}
