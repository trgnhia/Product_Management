package org.example.product_management.mapper;

import org.example.product_management.dto.product.request.ProductRequestDTO;
import org.example.product_management.dto.product.response.ProductResponseDTO;
import org.example.product_management.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping (source = "category.name", target = "categoryName")
    ProductResponseDTO toResponseDTO (Product product);
    List<ProductResponseDTO> toListResponseDTO(List<Product> productList);
    Product toEntity (ProductRequestDTO request);
    void updateEntityFromDto (ProductRequestDTO request, @MappingTarget Product product);
}

