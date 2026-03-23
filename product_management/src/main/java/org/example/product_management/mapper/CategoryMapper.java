package org.example.product_management.mapper;


import org.example.product_management.dto.request.CategoryRequestDTO;
import org.example.product_management.dto.response.CategoryResponseDTO;
import org.example.product_management.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDTO toResponseDTO (Category category);
    List<CategoryResponseDTO> toListResponseDTO(List<Category> categoryList);
    Category toEntity(CategoryRequestDTO request);
    void updateEntityFromDto (CategoryRequestDTO request, @MappingTarget Category category);
}
