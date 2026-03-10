package org.example.product_management.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {
    @NotBlank(message = "Category's name must not be blank")
    @Size(max = 100, message = "Category's name must not exceed 100 chars")
    private String name;
    @Size(max = 500, message = "Category's description must not exceed 500 chars")
    private String description;
}
