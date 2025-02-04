package com.twitterclone.user.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty(message = "El correo no puede estar vacío")
    @Email(message = "Correo electrónico no válido")
    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String passwordHash;
}
