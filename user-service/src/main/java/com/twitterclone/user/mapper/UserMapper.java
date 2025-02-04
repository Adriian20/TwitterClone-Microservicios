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
        userDTO.setFollowersCount(user.getFollowersCount());
        userDTO.setFollowingCount(user.getFollowingCount());
        return userDTO;

    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setBirthday(userDTO.getBirthday());
        user.setFollowersCount(userDTO.getFollowersCount());
        user.setFollowingCount(userDTO.getFollowingCount());
        return user;

    }
}
