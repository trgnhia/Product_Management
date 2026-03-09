package org.example.product_management.service.category;

import lombok.RequiredArgsConstructor;
import org.example.product_management.dto.category.CategoryRequestDTO;
import org.example.product_management.dto.category.CategoryResponseDTO;
import org.example.product_management.mapper.CategoryMapper;
import org.example.product_management.model.Category;
import org.example.product_management.repository.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> catrgories = categoryRepository.findAll();
        return mapper.toListResponseDTO(catrgories);
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryDto) {
        Category category = mapper.toEntity(categoryDto);
        categoryRepository.save(category);
        return mapper.toResponseDTO(category);
    }
}
