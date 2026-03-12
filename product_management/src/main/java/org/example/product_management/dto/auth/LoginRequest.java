package org.example.product_management.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "{user.email.not_blank}")
    private String email;
    @NotBlank(message = "{user.password.not_blank}")
    private String password;
}
