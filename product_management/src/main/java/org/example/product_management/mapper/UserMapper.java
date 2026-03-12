package org.example.product_management.mapper;

import org.example.product_management.dto.auth.RegisterRequest;
import org.example.product_management.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    User toEntity (RegisterRequest request);
}
