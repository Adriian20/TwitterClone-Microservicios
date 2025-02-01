package com.twitterclone.user.mapper;

import com.twitterclone.user.dto.UserDTO;
import com.twitterclone.user.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setFollowers(user.getFollowers());
        userDTO.setFollowing(user.getFollowing());
        return userDTO;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setBirthday(userDTO.getBirthday());
        user.setFollowers(userDTO.getFollowers());
        user.setFollowing(userDTO.getFollowing());
        return user;
    }
}
