package org.example.product_management.repository.category;

import org.example.product_management.dto.category.CategoryResponseDTO;
import org.example.product_management.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
