package com.ai.demo.service.impl;

import com.ai.demo.dto.request.ProductRequest;
import com.ai.demo.dto.response.ProductDetailResponse;
import com.ai.demo.dto.response.ProductSummaryResponse;
import com.ai.demo.entity.Category;
import com.ai.demo.entity.Product;
import com.ai.demo.exception.AppException;
import com.ai.demo.exception.ErrorCode;
import com.ai.demo.mapper.ProductMapper;
import com.ai.demo.repository.CategoryRepository;
import com.ai.demo.repository.ProductRepository;
import com.ai.demo.service.ProductService;
import com.ai.demo.util.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDetailResponse createProduct(ProductRequest request) {
        //Kiểm tra category có tồn tại k
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        //Tạo slug và kiểm tra trùng lặp slug k
        String generatedSlug = StringUtils.toSlug(request.getName());
        if(productRepository.existsBySlug(generatedSlug)){
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }

        //Map dữ liệu từ dto sang entity
        Product product = productMapper.toEntity(request);
        product.setCategory(category);
        product.setSlug(generatedSlug);

        Product saved = productRepository.save(product);
        return productMapper.toDetailResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductSummaryResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toSummaryResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductSummaryResponse> getProductByCategory(UUID categoryId) {
        return productRepository.findAllByCategoryIdOrderByCreatedAtDesc(categoryId)
                .stream()
                .map(productMapper::toSummaryResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetailResponse getProductById(UUID productId) {
        Product product = findByIdOrThrow(productId);
        return productMapper.toDetailResponse(product);
    }

    @Override
    public ProductDetailResponse updateProduct(UUID id, ProductRequest request) {
        Product product = findByIdOrThrow(id);

        if(request.getCategoryId() != null && !product.getCategory().getId().equals(request.getCategoryId())) {
            Category newCategory = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            product.setCategory(newCategory);
        }

        productMapper.updateProductFromRequest(product, request);

        if(request.getName() != null){
            String newSlug = StringUtils.toSlug(request.getName());
            if(!product.getSlug().equals(newSlug) && productRepository.existsBySlug(newSlug)){
                throw new AppException(ErrorCode.PRODUCT_EXISTED);
            }
            product.setSlug(newSlug);
        }
        Product saved = productRepository.save(product);
        return productMapper.toDetailResponse(saved);
    }

    @Override
    public void deleteProduct(UUID id) {
        Product product = findByIdOrThrow(id);
        product.setDeleted(true);
        product.setSlug(product.getSlug() + "-deleted-"+System.currentTimeMillis());
        productRepository.save(product);

    }

    private Product findByIdOrThrow(UUID id){
        return productRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }
}
