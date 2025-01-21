package com.twitterclone.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitterclone.user.dto.UserDTO;
import com.twitterclone.user.model.UserEntity;
import com.twitterclone.user.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getUsername().isEmpty() || userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()
                || userDTO.getBirthday() == null) {
            log.info("Invalid user {}", userDTO);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.registerUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> loginUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.loginUser(userDTO));
    }
}
