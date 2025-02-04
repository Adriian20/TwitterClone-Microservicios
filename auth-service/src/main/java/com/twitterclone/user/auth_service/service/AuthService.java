package com.twitterclone.user.auth_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.twitterclone.user.auth_service.dto.LoginRequest;
import com.twitterclone.user.auth_service.dto.RegisterRequest;
import com.twitterclone.user.auth_service.mapper.AuthMapper;
import com.twitterclone.user.auth_service.model.AuthEntity;
import com.twitterclone.user.auth_service.repository.AuthRepository;
import com.twitterclone.user.auth_service.util.JwtUtil;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AuthEntity registerUser(@Validated RegisterRequest registerRequest) {
        log.info("Registering in user: {}", registerRequest);
        return Optional.ofNullable(registerRequest)
                .map(requestRegister -> {
                    if (authRepository.existsByEmail(requestRegister.getEmail())) {
                        throw new IllegalArgumentException("El correo electrónico ya está registrado");
                    }
                    return authMapper.toEntity(requestRegister);
                })
                .map(authFound -> {
                    String token = JwtUtil.generateToken(authFound.getEmail());
                    authFound.setToken(token);
                    String encodedPassword = passwordEncoder.encode(authFound.getPasswordHash());
                    authFound.setPasswordHash(encodedPassword);
                    return authFound;
                })
                .map(authRepository::save)
                .orElseThrow(() -> new IllegalArgumentException("Datos de usuario inválidos"));
    }

    public AuthEntity loginUser(@Validated LoginRequest loginRequest) {
        log.info("Logging in user: {}", loginRequest);
        return Optional.ofNullable(loginRequest)
                .map(requestLogin -> {
                    if (authRepository.findByEmail(requestLogin.getEmail()) == null) {
                        throw new IllegalArgumentException("Usuario no encontrado");
                    }
                    return authMapper.toEntity(requestLogin);
                })
                .filter(authToken -> authToken.getToken() == null || authToken.getToken().isEmpty())
                .filter(authFound -> {
                    return passwordEncoder.matches(loginRequest.getPasswordHash(), authFound.getPasswordHash());
                })
                .map(authFound -> {
                    String token = JwtUtil.generateToken(authFound.getEmail());
                    authFound.setToken(token);
                    return authFound;
                })
                .map(authRepository::save)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));
    }

    public void logoutByToken(String token) {
        log.info("Cerrando sesión con token: {}", token);
        Optional.ofNullable(token)
                .filter(t -> !t.isEmpty())
                .map(t -> Optional.ofNullable(authRepository.findByToken(t))
                        .orElseThrow(() -> new IllegalArgumentException("Token no válido o usuario no encontrado")))
                .filter(user -> user.getToken() != null)
                .ifPresentOrElse(
                        user -> {
                            user.setToken(null);
                            authRepository.save(user);
                            log.info("Sesión cerrada exitosamente para el usuario: {}", user.getEmail());
                        },
                        () -> {
                            throw new IllegalStateException("El usuario ya ha cerrado sesión");
                        });
    }
}
