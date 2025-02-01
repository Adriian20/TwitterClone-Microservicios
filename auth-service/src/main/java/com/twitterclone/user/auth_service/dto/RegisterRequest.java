package com.twitterclone.user.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import java.time.LocalDate;

@Data
public class RegisterRequest {
    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String username;

    @NotEmpty(message = "El correo no puede estar vacío")
    @Email(message = "Correo electrónico no válido")
    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;

    @NotNull(message = "La fecha de cumpleaños no puede estar vacía")
    @Past(message = "La fecha de cumpleaños debe ser una fecha pasada")
    private LocalDate birthday;
}
