package com.twitterclone.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitterclone.user.dto.UserDTO;
import com.twitterclone.user.model.UserEntity;
import com.twitterclone.user.service.UserService;
import com.twitterclone.user.validation.ValidationGroups;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@Validated(ValidationGroups.Register.class) @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.registerUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> loginUser(@Validated(ValidationGroups.Login.class) @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.loginUser(userDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity<UserEntity> logoutUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.logoutUser(userDTO));
    }
}
