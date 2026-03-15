package org.example.product_management.service.security.impl;

import org.example.product_management.dto.auth.response.AuthResponse;
import org.example.product_management.dto.auth.request.LoginRequest;
import org.example.product_management.dto.auth.RegisterRequest;

public interface AuthService {
    void register (RegisterRequest request);
    AuthResponse login (LoginRequest request);
}
