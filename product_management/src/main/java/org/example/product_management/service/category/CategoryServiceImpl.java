package org.example.product_management.service.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.product_management.constant.ErrorMessages;
import org.example.product_management.dto.category.CategoryRequestDTO;
import org.example.product_management.dto.category.CategoryResponseDTO;
import org.example.product_management.exception.ResourceNotFoundException;
import org.example.product_management.mapper.CategoryMapper;
import org.example.product_management.model.Category;
import org.example.product_management.repository.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;
    private final CategoryMapper mapper;

    @Override
    @Transactional
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = repo.findAll();
        return mapper.toListResponseDTO(categories);
    }

    @Override
    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO request) {
        Category category = mapper.toEntity(request);
        repo.save(category);
        return mapper.toResponseDTO(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND + id));

        return mapper.toResponseDTO(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO update(CategoryRequestDTO request, Long id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND + id));
        mapper.updateEntityFromDto(request, category);
        repo.save(category);
        return mapper.toResponseDTO(category);
    }
}
