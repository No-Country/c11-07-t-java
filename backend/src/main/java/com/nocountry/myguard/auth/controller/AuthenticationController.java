package com.nocountry.myguard.auth.controller;

import com.nocountry.myguard.auth.model.authentication.AuthenticationRequest;
import com.nocountry.myguard.auth.model.authentication.AuthenticationResponse;
import com.nocountry.myguard.auth.model.authentication.RegisterRequest;
import com.nocountry.myguard.auth.service.AuthenticationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        try {
            return ResponseEntity.ok(service.register(request));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email){
        try {
            return new ResponseEntity<>(service.forgetPassword(email), HttpStatus.OK);
        } catch (MessagingException e){
            return new ResponseEntity("Unable to sent set password email. Please try again.", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
