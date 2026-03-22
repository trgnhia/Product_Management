package org.example.product_management.service.interfaces;

import org.example.product_management.dto.auth.request.RegisterRequest;
import org.example.product_management.dto.auth.response.AuthResponse;
import org.example.product_management.dto.auth.request.LoginRequest;

public interface AuthService {
    void register (RegisterRequest request);
    AuthResponse login (LoginRequest request);
}
