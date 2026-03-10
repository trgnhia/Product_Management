package org.example.product_management.controller.product;

import lombok.RequiredArgsConstructor;
import org.example.product_management.dto.product.ProductResponseDTO;
import org.example.product_management.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct () {
        List<ProductResponseDTO> products = service.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
