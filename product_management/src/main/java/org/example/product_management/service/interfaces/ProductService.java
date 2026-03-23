package org.example.product_management.service.interfaces;

import org.example.product_management.dto.response.PageResponse;
import org.example.product_management.dto.request.ProductRequestDTO;
import org.example.product_management.dto.response.ProductResponseDTO;

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
