package com.ai.demo.service;

import com.ai.demo.dto.request.CategoryRequest;
import com.ai.demo.dto.response.CategoryResponse;
import com.ai.demo.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category createCategory(CategoryRequest request);

    List<Category> getActiveCategories();

    List<Category> getAllCategories();

    Category getCategoryById(UUID id);

    Category updateCategory(UUID id, CategoryRequest request);

    CategoryResponse deleteCategory(UUID id);

}
