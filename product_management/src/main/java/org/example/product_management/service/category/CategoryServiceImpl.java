package org.example.product_management.service.category;

import lombok.RequiredArgsConstructor;
import org.example.product_management.dto.category.CategoryRequestDTO;
import org.example.product_management.dto.category.CategoryResponseDTO;
import org.example.product_management.mapper.CategoryMapper;
import org.example.product_management.model.Category;
import org.example.product_management.repository.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return mapper.toListResponseDTO(categories);
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO request) {
        Category category = mapper.toEntity(request);
        categoryRepository.save(category);
        return mapper.toResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return mapper.toResponseDTO(category);
    }
}
