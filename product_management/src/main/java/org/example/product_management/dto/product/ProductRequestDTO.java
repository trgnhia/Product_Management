package org.example.product_management.dto.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "Product's name must not be blank")
    @Size(max = 100, message = "Product's name must not exceed 100 chars")
    private String name;

    @NotNull(message = "Price must not null")
    @Positive(message = "Product's price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Quantity must not null")
    @PositiveOrZero(message = "Product's price must be greater than or equals 0")
    private Integer quantity;

    @Size(max = 500, message = "Product's description must not exceed 500 chars")
    private String description;

}
