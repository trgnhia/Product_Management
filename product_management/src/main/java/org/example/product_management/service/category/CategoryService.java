package org.example.product_management.service.category;

import org.example.product_management.dto.category.CategoryRequestDTO;
import org.example.product_management.dto.category.CategoryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO create(CategoryRequestDTO category);
    CategoryResponseDTO getCategoryById (Long id);
    CategoryResponseDTO update (CategoryRequestDTO request, Long id);
    void delete (Long id);
}
