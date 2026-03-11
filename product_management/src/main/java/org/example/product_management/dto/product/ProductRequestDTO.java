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

    @NotBlank(message = "{product.name.not_blank}")
    @Size(max = 100, message = "{product.name.size}")
    private String name;

    @NotNull(message = "{product.price.not_null}")
    @Positive(message = "{product.price.positive}")
    private BigDecimal price;

    @NotNull(message = "{product.quantity.not_null}")
    @PositiveOrZero(message = "{product.quantity.positive_or_zero}")
    private Integer quantity;

    @Size(max = 500, message = "{product.description.size}")
    private String description;

    @NotNull(message = "{product.category_id.not_null}")
    private Long categoryId;
}