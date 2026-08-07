package ro.fittrack.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.fittrack.auth.dto.RegisterResponse;
import ro.fittrack.auth.dto.RegisterRequest;
import ro.fittrack.auth.entity.User;
import ro.fittrack.auth.repository.UserRepository;
import ro.fittrack.auth.service.AuthService;
import ro.fittrack.auth.dto.LoginRequest;
import ro.fittrack.auth.dto.LoginResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor

public class AuthController{
    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public RegisterResponse me(Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new RegisterResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
    @GetMapping("/test")
    public String test() {
        return "Authenticated successfully!";
    }
}
