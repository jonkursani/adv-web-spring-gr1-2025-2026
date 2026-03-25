package dev.jonkursani.restapigr1.controllers;

import dev.jonkursani.restapigr1.dtos.auth.AuthResponse;
import dev.jonkursani.restapigr1.dtos.auth.LoginRequest;
import dev.jonkursani.restapigr1.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoints", description = "Endpoints related to user login and register")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        // autentifikimi i userit
        var user = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        // gjenerimi i tokenit
        var token = authService.generateToken(user);

        var response = AuthResponse.builder()
                .token(token)
                .expiresIn(86400) // 24h => 86400 seconds
//                .expiresIn(900000) // 15min => 900,000 ms
                .build();

        return ResponseEntity.ok(response);
    }
}