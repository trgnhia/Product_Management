package org.example.product_management.mapper;


import org.example.product_management.dto.category.CategoryRequestDTO;
import org.example.product_management.dto.category.CategoryResponseDTO;
import org.example.product_management.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDTO toResponseDTO (Category category);
    List<CategoryResponseDTO> toListResponseDTO(List<Category> categoryList);
    Category toEntity(CategoryRequestDTO dto);
}
