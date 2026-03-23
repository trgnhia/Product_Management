package org.example.product_management.service.interfaces;

import org.example.product_management.dto.request.CategoryRequestDTO;
import org.example.product_management.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO create(CategoryRequestDTO category);
    CategoryResponseDTO getCategoryById (Long id);
    CategoryResponseDTO update (CategoryRequestDTO request, Long id);
    void delete (Long id);
}
