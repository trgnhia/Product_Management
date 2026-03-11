package org.example.product_management.repository.product;

import org.example.product_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCategory_Id(Long categoryId);
}
