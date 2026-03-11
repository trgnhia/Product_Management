package org.example.product_management.controller.product;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<PageResponse<ProductResponseDTO>> getProducts(@RequestParam(defaultValue = "0") @Min(0)  int page,
                                                                        @RequestParam(defaultValue = "5") @Min(1)  @Max(50)int size) {
        return ResponseEntity.ok(service.getProducts(page, size));
    }

    @GetMapping("/sorted")
    public ResponseEntity<PageResponse<ProductResponseDTO>> getSortedProducts (@RequestParam(defaultValue = "0") @Min(0)  int page,
                                                                               @RequestParam(defaultValue = "5") @Min(1)  @Max(50)int size,
                                                                               @RequestParam(defaultValue = "name")  String sortBy,
                                                                               @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(service.getSortingProducts(page, size, sortBy, direction));
    }

}
