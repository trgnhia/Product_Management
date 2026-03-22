package org.example.product_management.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.product_management.constant.ErrorMessages;
import org.example.product_management.dto.auth.request.RegisterRequest;
import org.example.product_management.dto.auth.response.AuthResponse;
import org.example.product_management.dto.auth.request.LoginRequest;
import org.example.product_management.enums.Role;
import org.example.product_management.exception.InvalidCredentialsException;
import org.example.product_management.exception.ResourceConflictException;
import org.example.product_management.mapper.UserMapper;
import org.example.product_management.entity.User;
import org.example.product_management.repository.user.UserRepository;
import org.example.product_management.service.interfaces.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceConflictException(ErrorMessages.USERNAME_ALREADY_EXIST);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceConflictException(ErrorMessages.EMAIL_ALREADY_EXIST);
        }
        User user = mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new InvalidCredentialsException(ErrorMessages.INVALID_CREDENTIALS);
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException(ErrorMessages.INVALID_CREDENTIALS));

        String accessToken = jwtService.issueAccessToken(user, Duration.ofHours(2));

        return AuthResponse.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }
}
