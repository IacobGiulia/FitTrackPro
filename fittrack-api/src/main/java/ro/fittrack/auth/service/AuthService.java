package ro.fittrack.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.fittrack.auth.dto.RegisterResponse;
import ro.fittrack.auth.dto.RegisterRequest;
import ro.fittrack.auth.entity.User;
import ro.fittrack.auth.repository.UserRepository;
import ro.fittrack.auth.dto.LoginRequest;
import ro.fittrack.auth.dto.LoginResponse;
import ro.fittrack.auth.security.JwtService;

@Service
@RequiredArgsConstructor

public class AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException("Email already in use");
        }
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);
    }

    public RegisterResponse getCurrentUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new RegisterResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}