package dev.jonkursani.restapigr1.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}