package org.example.product_management.service.security;

import org.example.product_management.dto.auth.AuthResponse;
import org.example.product_management.dto.auth.LoginRequest;
import org.example.product_management.dto.auth.RegisterRequest;

public interface AuthService {
    void register (RegisterRequest request);
    AuthResponse login (LoginRequest request);
}
