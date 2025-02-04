package com.twitterclone.user.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class UserDTO implements Serializable {
    private Long id;

    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String username;

    @NotEmpty(message = "El correo no puede estar vacío")
    @Email(message = "Correo electrónico no válido")
    private String email;

    @NotNull(message = "La fecha de cumpleaños no puede estar vacía")
    @Past(message = "La fecha de cumpleaños debe ser una fecha pasada")
    private LocalDate birthday;

    private int followersCount;
    private int followingCount;
    private String token;
}
