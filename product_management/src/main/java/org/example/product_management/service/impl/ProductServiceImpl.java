package org.example.product_management.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.product_management.constant.ErrorMessages;
import org.example.product_management.dto.page.PageResponse;
import org.example.product_management.dto.product.request.ProductRequestDTO;
import org.example.product_management.dto.product.response.ProductResponseDTO;
import org.example.product_management.exception.ResourceNotFoundException;
import org.example.product_management.mapper.ProductMapper;
import org.example.product_management.entity.Category;
import org.example.product_management.entity.Product;
import org.example.product_management.repository.category.CategoryRepository;
import org.example.product_management.repository.product.ProductRepository;
import org.example.product_management.service.interfaces.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
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
        Product product = findProductById(id);
        ProductResponseDTO respone = mapper.toResponseDTO(product);
        return respone;
    }

//    @Override
//    public ProductResponseDTO create(ProductRequestDTO request) {
//        Long categoryId = request.getCategoryId();
//        Category category = findCategoryById(categoryId);
//        Product product = mapper.toEntity(request);
//        product.setCategory(category);
//        productRepo.save(product);
//        return mapper.toResponseDTO(product);
//    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO request) {
        log.info("Creating product with name={}, categoryId={}", request.getName(), request.getCategoryId());

        Long categoryId = request.getCategoryId();
        Category category = findCategoryById(categoryId);

        Product product = mapper.toEntity(request);
        product.setCategory(category);

        Product savedProduct = productRepo.save(product);

        log.info("Product created successfully with id={}", savedProduct.getId());

        return mapper.toResponseDTO(savedProduct);
    }


    @Override
    public ProductResponseDTO update(ProductRequestDTO request, Long id) {
        Product product = findProductById(id);
        Long categoryId = request.getCategoryId();
        Category category = findCategoryById(categoryId);
        mapper.updateEntityFromDto(request, product);
        product.setCategory(category);
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

    @Override
    public PageResponse<ProductResponseDTO> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepo.findAll(pageable);

        List<ProductResponseDTO> content = mapper.toListResponseDTO(productPage.getContent());
        PageResponse response = new PageResponse (
                content,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
        return response;
    }

    @Override
    public PageResponse<ProductResponseDTO> getSortingProducts(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepo.findAll(pageable);
        List<ProductResponseDTO> content = mapper.toListResponseDTO(productPage.getContent());
        return new PageResponse<>(
                content,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }


    /**
     *
     * @param id
     * @return
     */
    private Category findCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND + id));
    }

    private Product findProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND + id));
    }
}
