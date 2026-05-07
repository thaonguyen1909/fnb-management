package com.ai.demo.service;

import com.ai.demo.dto.request.ProductRequest;
import com.ai.demo.dto.response.ProductDetailResponse;
import com.ai.demo.dto.response.ProductSummaryResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDetailResponse createProduct(ProductRequest request);
    List<ProductSummaryResponse> getAllProduct();
    List<ProductSummaryResponse> getProductByCategory(UUID categoryId);
    ProductDetailResponse getProductById(UUID productId);
    ProductDetailResponse updateProduct(UUID id, ProductRequest request);
    void deleteProduct(UUID id);
}
