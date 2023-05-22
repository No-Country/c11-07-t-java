package com.nocountry.myguard.service.security;

import com.nocountry.myguard.controllers.security.AuthenticationRequest;
import com.nocountry.myguard.controllers.security.AuthenticationResponse;
import com.nocountry.myguard.controllers.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request) {
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
    }
}
