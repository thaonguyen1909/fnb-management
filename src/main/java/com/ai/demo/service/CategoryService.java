package com.ai.demo.service;

import com.ai.demo.dto.request.CategoryRequest;
import com.ai.demo.dto.response.CategoryDeleteResponse;
import com.ai.demo.dto.response.CategoryDetailResponse;
import com.ai.demo.dto.response.CategoryResponse;
import com.ai.demo.dto.response.CategorySummaryResponse;
import com.ai.demo.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDetailResponse createCategory(CategoryRequest request);

    List<CategorySummaryResponse> getActiveCategories();

    List<CategorySummaryResponse> getAllCategories();

    CategoryDetailResponse getCategoryById(UUID id);

    CategoryDetailResponse updateCategory(UUID id, CategoryRequest request);

    CategoryDeleteResponse deleteCategory(UUID id);

}
