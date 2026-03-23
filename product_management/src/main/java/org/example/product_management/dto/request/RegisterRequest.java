package org.example.product_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "{user.email.not_blank}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @NotBlank(message = "{user.password.not_blank}")
    @Size(min = 6, max = 50, message = "Username must be between 6 and 50 characters")
    private String password;

    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @NotBlank(message = "{user.username.not_blank}")
    private String username;
}
