package com.twitterclone.user.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitterclone.user.auth_service.dto.LoginRequest;
import com.twitterclone.user.auth_service.dto.RegisterRequest;
import com.twitterclone.user.auth_service.model.AuthEntity;
import com.twitterclone.user.auth_service.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthEntity> registerUser(@Validated @RequestBody RegisterRequest registerDTO) {
        return ResponseEntity.ok(authService.registerUser(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthEntity> loginUser(@Validated @RequestBody LoginRequest loginDTO) {
        return ResponseEntity.ok(authService.loginUser(loginDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String token) {
        String cleanedToken = token.replace("Bearer ", "");
        authService.logoutByToken(cleanedToken);
        return ResponseEntity.ok("Logout exitoso");
    }
}
