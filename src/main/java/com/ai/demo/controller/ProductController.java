package com.ai.demo.controller;

import com.ai.demo.dto.request.ProductRequest;
import com.ai.demo.dto.response.ApiResponse;
import com.ai.demo.dto.response.CategoryDetailResponse;
import com.ai.demo.dto.response.ProductDetailResponse;
import com.ai.demo.dto.response.ProductSummaryResponse;
import com.ai.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController {
    private final RestClient.Builder builder;
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductDetailResponse> createProduct(@Valid @RequestBody ProductRequest request){
        return ApiResponse.<ProductDetailResponse>builder()
                .code(1000)
                .message("Product created successfully")
                .result(productService.createProduct(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductSummaryResponse>> getAllProduct(){
        return ApiResponse.<List<ProductSummaryResponse>>builder()
                .code(1000)
                .message("Get all products successfully")
                .result(productService.getAllProduct())
                .build();
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<List<ProductSummaryResponse>> getProductByCategory(@PathVariable UUID categoryId){
        return ApiResponse.<List<ProductSummaryResponse>>builder()
                .code(1000)
                .message("Get product by category successfully")
                .result(productService.getProductByCategory(categoryId))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDetailResponse> getProductById(@PathVariable UUID id){
        return ApiResponse.<ProductDetailResponse>builder()
                .code(1000)
                .message("Get product by id successfully")
                .result(productService.getProductById(id))
                .build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ApiResponse<ProductDetailResponse> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductRequest request){
        return ApiResponse.<ProductDetailResponse>builder()
                .code(1000)
                .message("Update product successfully")
                .result(productService.updateProduct(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);

    }

}
