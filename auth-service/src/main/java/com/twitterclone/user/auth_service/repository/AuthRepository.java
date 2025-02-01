package com.twitterclone.user.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitterclone.user.auth_service.model.AuthEntity;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    AuthEntity findByEmail(String email);

    AuthEntity findByToken(String token);

    AuthEntity findByUsername(String username);

    boolean existsByEmail(String email);
}
