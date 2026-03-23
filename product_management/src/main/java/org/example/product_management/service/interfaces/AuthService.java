package org.example.product_management.service.interfaces;

import org.example.product_management.dto.request.RegisterRequest;
import org.example.product_management.dto.response.AuthResponse;
import org.example.product_management.dto.request.LoginRequest;

public interface AuthService {
    void register (RegisterRequest request);
    AuthResponse login (LoginRequest request);
}
