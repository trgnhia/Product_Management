package org.example.product_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.product_management.constant.SuccessMessages;
import org.example.product_management.dto.ApiResponse;
import org.example.product_management.dto.response.PageResponse;
import org.example.product_management.dto.request.ProductRequestDTO;
import org.example.product_management.dto.response.ProductResponseDTO;
import org.example.product_management.service.interfaces.ProductService;
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
@Tag(name = "Product API", description = "APIs for managing products")
public class ProductController {

    private final ProductService service;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAllProduct() {
        List<ProductResponseDTO> products = service.getAllProducts();
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), products, SuccessMessages.PRODUCT_RETRIEVED)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(@PathVariable Long id) {
        ProductResponseDTO response = service.getProductById(id);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), response, SuccessMessages.PRODUCT_RETRIEVED)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> create(@Valid @RequestBody ProductRequestDTO request) {
        ProductResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), response, SuccessMessages.PRODUCT_CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> update(
            @Valid @RequestBody ProductRequestDTO request,
            @PathVariable Long id
    ) {
        ProductResponseDTO response = service.update(request, id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), response, SuccessMessages.PRODUCT_UPDATED));
    }

    @Operation(
            summary = "Delete product",
            description = "Delete product by id"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).
                body(ApiResponse.success(HttpStatus.NO_CONTENT.value(), null, SuccessMessages.PRODUCT_DELETED));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProductResponseDTO>>> getProducts(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "5") @Min(1) @Max(50) int size
    ) {
        PageResponse<ProductResponseDTO> response = service.getProducts(page, size);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), response, SuccessMessages.PRODUCT_RETRIEVED));
    }

    @Operation(
            summary = "Get sorted products",
            description = "Retrieve products with pagination and sorting"
    )
    @GetMapping("/sorted")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponseDTO>>> getSortedProducts(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "5") @Min(1) @Max(50) int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        PageResponse<ProductResponseDTO> response = service.getSortingProducts(page, size, sortBy, direction);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), response, SuccessMessages.PRODUCT_RETRIEVED));
    }
}