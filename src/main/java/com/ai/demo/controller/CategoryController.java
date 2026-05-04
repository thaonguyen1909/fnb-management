package com.ai.demo.controller;

import com.ai.demo.dto.request.CategoryRequest;
import com.ai.demo.dto.response.*;
import com.ai.demo.entity.Category;
import com.ai.demo.repository.CategoryRepository;
import com.ai.demo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryDetailResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryDetailResponse>builder()
                .code(1000)
                .message("Create category successfully")
                .result(categoryService.createCategory(request))
                .build();
    }

    @GetMapping("/active")
    public ApiResponse<List<CategorySummaryResponse>> getActiveCategories(){
        return ApiResponse.<List<CategorySummaryResponse>>builder()
                .code(1000)
                .message("Get active categories successfully")
                .result(categoryService.getActiveCategories())
                .build();
    }

    @GetMapping
    public ApiResponse<List<CategorySummaryResponse>> getAllCategories(){
        return ApiResponse.<List<CategorySummaryResponse>>builder()
                .code(1000)
                .message("Get all categories successfully")
                .result(categoryService.getAllCategories())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryDetailResponse> getCategoryById(@PathVariable UUID id){
        return ApiResponse.<CategoryDetailResponse>builder()
                .code(1000)
                .message("Get category by id successfully")
                .result(categoryService.getCategoryById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryDetailResponse> updateCategory(@PathVariable UUID id, @Valid @RequestBody CategoryRequest request){
        return ApiResponse.<CategoryDetailResponse>builder()
                .code(1000)
                .message("Update category successfully")
                .result(categoryService.updateCategory(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<CategoryDeleteResponse> deleteCategory(@PathVariable UUID id){
        return ApiResponse.<CategoryDeleteResponse>builder()
                .code(1000)
                .message("Delete category successfully")
                .result(categoryService.deleteCategory(id))
                .build();
    }

}
