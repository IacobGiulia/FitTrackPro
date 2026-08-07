package ro.fittrack.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.fittrack.auth.dto.RegisterResponse;
import ro.fittrack.auth.dto.RegisterRequest;
import ro.fittrack.auth.entity.User;
import ro.fittrack.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor

public class AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}