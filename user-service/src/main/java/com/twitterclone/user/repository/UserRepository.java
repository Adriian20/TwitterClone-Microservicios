package com.twitterclone.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitterclone.user.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
