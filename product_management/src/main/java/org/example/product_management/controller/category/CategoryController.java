package org.example.product_management.controller.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.product_management.constant.SuccessMessages;
import org.example.product_management.dto.ApiResponse;
import org.example.product_management.dto.category.request.CategoryRequestDTO;
import org.example.product_management.dto.category.response.CategoryResponseDTO;
import org.example.product_management.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Category API", description = "APIs for managing categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(
            summary = "Get all categories",
            description = "Retrieve all categories from the system"
    )
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(
                ApiResponse.<List<CategoryResponseDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message(SuccessMessages.CATEGORY_RETRIEVED)
                        .data(categories)
                        .build()
        );
    }
    @Operation(
            summary = "Create category",
            description = "Create a new category"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> create(@Valid @RequestBody CategoryRequestDTO categoryRequest) {
        CategoryResponseDTO response = categoryService.create(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CategoryResponseDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message(SuccessMessages.CATEGORY_CREATED)
                        .data(response)
                        .build());
    }
    @Operation(
            summary = "Get category by id",
            description = "Retrieve category information by its id"
    )
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategoryById(@PathVariable Long id) {
        CategoryResponseDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(
                ApiResponse.<CategoryResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message(SuccessMessages.CATEGORY_RETRIEVED)
                        .data(category)
                        .build()
        );
    }

    @Operation(
            summary = "Update category",
            description = "Update category information by id"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> update(
            @Valid @RequestBody CategoryRequestDTO request,
            @PathVariable Long id
    ) {
        CategoryResponseDTO response = categoryService.update(request, id);
        return ResponseEntity.ok(
                ApiResponse.<CategoryResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message(SuccessMessages.CATEGORY_UPDATED)
                        .data(response)
                        .build()
        );
    }

    @Operation(
            summary = "Delete category",
            description = "Delete category by id"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message(SuccessMessages.CATEGORY_DELETED)
                        .data(null)
                        .build());
    }
}