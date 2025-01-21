package com.twitterclone.user.model;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users", schema = "user-service")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String username;
    
    @Basic
    private String email;

    @Basic
    private String password;

    @Basic
    private LocalDate birthday;

    @Basic
    private int followers;

    @Basic
    private int following;
}
