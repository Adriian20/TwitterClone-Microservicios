package com.twitterclone.user.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.twitterclone.user.model.UserEntity;
import com.twitterclone.user.validation.ValidationGroups;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class UserDTO implements Serializable {
    private Long id;

    @NotEmpty(message = "El nombre de usuario no puede estar vacío", groups = ValidationGroups.Register.class)
    private String username;

    @NotEmpty(message = "El correo no puede estar vacío", groups = { ValidationGroups.Register.class,
            ValidationGroups.Login.class })
    @Email(message = "Correo electrónico no válido", groups = { ValidationGroups.Register.class,
            ValidationGroups.Login.class })
    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía", groups = { ValidationGroups.Register.class,
            ValidationGroups.Login.class })
    private String password;

    @NotNull(message = "La fecha de cumpleaños no puede estar vacía", groups = ValidationGroups.Register.class)
    @Past(message = "La fecha de cumpleaños debe ser una fecha pasada", groups = ValidationGroups.Register.class)
    private LocalDate birthday;

    private int followers;
    private int following;
    private String token;

    public static UserDTO convertToDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setFollowers(user.getFollowers());
        userDTO.setFollowing(user.getFollowing());
        userDTO.setToken(user.getToken());

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
        user.setToken(userDTO.getToken());

        return user;
    }

    public UserDTO() {
        super();
    }
}
