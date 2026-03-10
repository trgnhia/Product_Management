package org.example.product_management.service.product;

import lombok.RequiredArgsConstructor;
import org.example.product_management.dto.product.ProductRequestDTO;
import org.example.product_management.dto.product.ProductResponseDTO;
import org.example.product_management.mapper.ProductMapper;
import org.example.product_management.model.Product;
import org.example.product_management.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repo;
    private final ProductMapper mapper;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products  = repo.findAll();
        return mapper.toListResponseDTO(products);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return null;
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO request) {
        return null;
    }

    @Override
    public ProductResponseDTO update(ProductRequestDTO request, Product product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
