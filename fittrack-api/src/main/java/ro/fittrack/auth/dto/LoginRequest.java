package ro.fittrack.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(

        @Email(message="Invalid email")
        @NotBlank(message="Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password
){}