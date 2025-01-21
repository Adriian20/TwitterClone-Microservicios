package com.twitterclone.user.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.twitterclone.user.model.UserEntity;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate birthday;
    private int followers;
    private int following;

    public static UserDTO convertToDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setFollowers(user.getFollowers());
        userDTO.setFollowing(user.getFollowing());

        return userDTO;
    }

    public static UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setBirthday(userDTO.getBirthday());
        user.setFollowers(userDTO.getFollowers());
        user.setFollowing(userDTO.getFollowing());

        return user;
    }

    public UserDTO() {
        super();
    }
}
