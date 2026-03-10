package org.example.product_management.service.product;

import lombok.RequiredArgsConstructor;
import org.example.product_management.constant.ErrorMessages;
import org.example.product_management.dto.product.ProductRequestDTO;
import org.example.product_management.dto.product.ProductResponseDTO;
import org.example.product_management.exception.ResourceNotFoundException;
import org.example.product_management.mapper.ProductMapper;
import org.example.product_management.model.Category;
import org.example.product_management.model.Product;
import org.example.product_management.repository.category.CategoryRepository;
import org.example.product_management.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepo;
    private final ProductMapper mapper;
    private final CategoryRepository categoryRepo;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products  = productRepo.findAll();
        return mapper.toListResponseDTO(products);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + id));
        ProductResponseDTO respone = mapper.toResponseDTO(product);
        return respone;
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO request) {
        //Category category = categoryRepo.findById(request.getCategoryId())
        Product product = mapper.toEntity(request);
        productRepo.save(product);
        return mapper.toResponseDTO(product);
    }

    @Override
    public ProductResponseDTO update(ProductRequestDTO request, Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + id));
        mapper.updateEntityFromDto(request, product);
        productRepo.save(product);
        return mapper.toResponseDTO(product);
    }

    @Override
    public void delete(Long id) {
        if (!productRepo.existsById(id)) {
            throw new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + id);
        }
        productRepo.deleteById(id);
    }
}
