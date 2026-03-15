package org.example.product_management.dto.category.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {
    @NotBlank(message = "{category.name.not_blank}")
    @Size(max = 100, message = "{category.name.size}")
    private String name;
    @Size(max = 500, message = "category.description.size")
    private String description;
}
