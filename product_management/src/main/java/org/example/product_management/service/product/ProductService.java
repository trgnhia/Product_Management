package org.example.product_management.service.product;

import org.example.product_management.dto.page.PageResponse;
import org.example.product_management.dto.product.ProductRequestDTO;
import org.example.product_management.dto.product.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO create(ProductRequestDTO request);
    ProductResponseDTO update (ProductRequestDTO request, Long id);
    void delete(Long id);
    PageResponse<ProductResponseDTO> getProducts (int page, int size);
    PageResponse<ProductResponseDTO> getSortingProducts (int page, int size, String sortBy, String direction);
}
