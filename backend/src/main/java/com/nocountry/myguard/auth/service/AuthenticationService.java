package com.nocountry.myguard.auth.service;

import com.nocountry.myguard.auth.Util.EmailUtil;
import com.nocountry.myguard.auth.model.authentication.AuthenticationRequest;
import com.nocountry.myguard.auth.model.authentication.AuthenticationResponse;
import com.nocountry.myguard.auth.model.authentication.RegisterRequest;
import com.nocountry.myguard.enums.Role;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    private EmailUtil emailUtil;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        Optional<User> optUser = userRepository.findByUsername(request.getUsername());

        if (optUser.isPresent())
            throw new RuntimeException("This username has been already taken.");

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
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
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public String forgetPassword(String email) throws Exception {


        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new Exception("User not found with this email: " + email));

        emailUtil.sendSetPasswordEmail(email);

        return "Email send. Please check your email to set new password to your account.";
    }

    public String setPassword(String email, String newPassword) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new Exception("User not found with this email: " + email));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "Password changed successfully.";

    }
}
