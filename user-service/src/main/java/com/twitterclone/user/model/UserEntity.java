package com.twitterclone.user.model;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users", schema = "twitter_clone")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "username", nullable = false)
    private String username;
    
    @Basic
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Basic
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Basic
    @Column(name = "followers_count", nullable = true)
    private int followersCount;

    @Basic
    @Column(name = "following_count", nullable = true)
    private int followingCount;

    @Basic
    @Column(name = "token", nullable = true)
    private String token;
}
