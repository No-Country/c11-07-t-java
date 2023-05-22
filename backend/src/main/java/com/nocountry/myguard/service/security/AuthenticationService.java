package com.nocountry.myguard.service.security;

import com.nocountry.myguard.controllers.security.AuthenticationRequest;
import com.nocountry.myguard.controllers.security.AuthenticationResponse;
import com.nocountry.myguard.controllers.security.RegisterRequest;
import com.nocountry.myguard.enums.Role;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PROFESSIONAL)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
