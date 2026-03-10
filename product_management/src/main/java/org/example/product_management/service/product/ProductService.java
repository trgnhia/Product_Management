package org.example.product_management.service.product;

import org.example.product_management.dto.product.ProductRequestDTO;
import org.example.product_management.dto.product.ProductResponseDTO;
import org.example.product_management.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO create(ProductRequestDTO request);
    ProductResponseDTO update (ProductRequestDTO request, Product product);
    void delete(Long id);
}
