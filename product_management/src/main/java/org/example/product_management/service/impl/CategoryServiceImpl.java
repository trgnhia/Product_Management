package org.example.product_management.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.product_management.constant.ErrorMessages;
import org.example.product_management.dto.category.request.CategoryRequestDTO;
import org.example.product_management.dto.category.response.CategoryResponseDTO;
import org.example.product_management.exception.ResourceConflictException;
import org.example.product_management.exception.ResourceNotFoundException;
import org.example.product_management.mapper.CategoryMapper;
import org.example.product_management.entity.Category;
import org.example.product_management.repository.category.CategoryRepository;
import org.example.product_management.repository.product.ProductRepository;
import org.example.product_management.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final CategoryMapper mapper;

    @Override
    @Transactional
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return mapper.toListResponseDTO(categories);
    }

    @Override
    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO request) {
        Category category = mapper.toEntity(request);
        categoryRepo.save(category);
        return mapper.toResponseDTO(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = findCategoryById(id);
        return mapper.toResponseDTO(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO update(CategoryRequestDTO request, Long id) {
        Category category = findCategoryById(id);
        mapper.updateEntityFromDto(request, category);
        categoryRepo.save(category);
        return mapper.toResponseDTO(category);
    }

    @Override
    public void delete(Long id) {
        Category category = findCategoryById(id);
        if (productRepo.existsByCategory_Id(id)) {
            throw new ResourceConflictException(ErrorMessages.CATEGORY_HAS_PRODUCTS);
        }
        categoryRepo.delete(category);
    }

    // -------------helper----------//
    private Category findCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND + id));
    }
}
