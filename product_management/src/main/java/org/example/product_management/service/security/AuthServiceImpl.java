package org.example.product_management.service.security;

import lombok.RequiredArgsConstructor;
import org.example.product_management.dto.auth.AuthResponse;
import org.example.product_management.dto.auth.LoginRequest;
import org.example.product_management.dto.auth.RegisterRequest;
import org.example.product_management.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {

        }

        if (userRepository.existsByEmail(request.getEmail())) {

        }
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
