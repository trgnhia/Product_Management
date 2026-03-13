package org.example.product_management.controller.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.product_management.dto.page.PageResponse;
import org.example.product_management.dto.product.ProductRequestDTO;
import org.example.product_management.dto.product.ProductResponseDTO;
import org.example.product_management.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
@Tag(name = "Product API", description = "APTs for managing products")
public class ProductController {
    private final ProductService service;


    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct() {
        List<ProductResponseDTO> products = service.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO response = service.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO request) {
        ProductResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@Valid @RequestBody ProductRequestDTO request, @PathVariable Long id) {
        ProductResponseDTO response = service.update(request, id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete product",
            description = "Delete product by id"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<PageResponse<ProductResponseDTO>> getProducts(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                        @RequestParam(defaultValue = "5") @Min(1) @Max(50) int size) {
        return ResponseEntity.ok(service.getProducts(page, size));
    }

    @Operation(
            summary = "Get sorted products",
            description = "Retrieve products with pagination and sorting"
    )
    @GetMapping("/sorted")
    public ResponseEntity<PageResponse<ProductResponseDTO>> getSortedProducts(
            @Parameter(description = "Page number", example = "0")
            @RequestParam(defaultValue = "0") @Min(0) int page,

            @Parameter(description = "Page size", example = "5")
            @RequestParam(defaultValue = "5") @Min(1) @Max(50) int size,

            @Parameter(description = "Field used for sorting", example = "name")
            @RequestParam(defaultValue = "name") String sortBy,

            @Parameter(description = "Sorting direction", example = "asc")
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(service.getSortingProducts(page, size, sortBy, direction));
    }

}
