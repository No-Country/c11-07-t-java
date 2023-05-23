package com.nocountry.myguard.auth.service;

import com.nocountry.myguard.auth.model.authentication.AuthenticationRequest;
import com.nocountry.myguard.auth.model.authentication.AuthenticationResponse;
import com.nocountry.myguard.auth.model.authentication.RegisterRequest;
import com.nocountry.myguard.enums.Role;
import com.nocountry.myguard.auth.model.User;
import com.nocountry.myguard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        Optional<User> optUser = repository.findByUsername(request.getUsername());

        if (optUser.isPresent())
            throw new RuntimeException("This username has been already taken.");

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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        //It will continue to below lines only if authentication was successful
        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
