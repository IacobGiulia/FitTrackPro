package ro.fittrack.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.fittrack.auth.dto.RegisterResponse;
import ro.fittrack.auth.dto.RegisterRequest;
import ro.fittrack.auth.service.AuthService;
import ro.fittrack.auth.dto.LoginRequest;
import ro.fittrack.auth.dto.LoginResponse;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor

public class AuthController{
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/test")
    public String test() {
        return "Authenticated successfully!";
    }
}
