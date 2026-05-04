package com.ai.demo.controller;

import com.ai.demo.dto.request.CategoryRequest;
import com.ai.demo.dto.response.ApiResponse;
import com.ai.demo.dto.response.CategoryResponse;
import com.ai.demo.entity.Category;
import com.ai.demo.repository.CategoryRepository;
import com.ai.demo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
    public ApiResponse<Category> createCategory(@Valid @RequestBody CategoryRequest request) {
        Category category = categoryService.createCategory(request);
        return ApiResponse.<Category>builder()
                .code(1000)
                .message("Create category successfully")
                .result(category)
                .build();
    }

    @GetMapping("/active")
    public ApiResponse<List<Category>> getActiveCategories(){
        return ApiResponse.<List<Category>>builder()
                .code(1000)
                .message("Get active categories successfully")
                .result(categoryService.getActiveCategories())
                .build();
    }

    @GetMapping
    public ApiResponse<List<Category>> getAllCategories(){
        return ApiResponse.<List<Category>>builder()
                .code(1000)
                .message("Get all categories successfully")
                .result(categoryService.getAllCategories())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Category> getCategoryById(@PathVariable UUID id){
        return ApiResponse.<Category>builder()
                .code(1000)
                .message("Get category by id successfully")
                .result(categoryService.getCategoryById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Category> updateCategory(@PathVariable UUID id, @Valid @RequestBody CategoryRequest request){
        return ApiResponse.<Category>builder()
                .code(1000)
                .message("Update category successfully")
                .result(categoryService.updateCategory(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<CategoryResponse> deleteCategory(@PathVariable UUID id){
        return ApiResponse.<CategoryResponse>builder()
                .code(1000)
                .message("Delete category successfully")
                .result(categoryService.deleteCategory(id))
                .build();
    }

}
