package org.example.product_management.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.product_management.dto.auth.AuthResponse;
import org.example.product_management.dto.auth.LoginRequest;
import org.example.product_management.dto.auth.RegisterRequest;
import org.example.product_management.service.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}