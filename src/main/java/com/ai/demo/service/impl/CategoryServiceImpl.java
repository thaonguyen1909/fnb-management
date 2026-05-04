package com.ai.demo.service.impl;

import com.ai.demo.dto.request.CategoryRequest;
import com.ai.demo.dto.response.CategoryResponse;
import com.ai.demo.entity.Category;
import com.ai.demo.exception.AppException;
import com.ai.demo.exception.ErrorCode;
import com.ai.demo.mapper.CategoryMapper;
import com.ai.demo.repository.CategoryRepository;
import com.ai.demo.service.CategoryService;
import com.ai.demo.util.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public Category createCategory(CategoryRequest request) {
        String generatedSlug = StringUtils.toSlug(request.getName());

        if(categoryRepository.existsBySlug(generatedSlug)){
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        Category category = Category.builder()
                .name(request.getName())
                .slug(generatedSlug)
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getActiveCategories() {
        return categoryRepository.findAllByAndIsActiveTrueOrderByDisplayOrderAsc();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllByOrderByDisplayOrderAsc();
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public Category updateCategory(UUID id, CategoryRequest request) {
        Category category = getCategoryById(id);

        String newSlug = StringUtils.toSlug(request.getName());

        if(!category.getSlug().equals(newSlug) && categoryRepository.existsBySlug(newSlug)){
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        category.setName(request.getName());
        category.setSlug(newSlug);

        if(request.getIsActive() != null) {
            category.setIsActive(request.getIsActive());
        }

        if(request.getDisplayOrder() != null) {
            category.setDisplayOrder(request.getDisplayOrder());
        }

        return categoryRepository.save(category);
    }

    @Override
    public CategoryResponse deleteCategory(UUID id) {
        Category category = getCategoryById(id);

        category.setDeleted(true);

        category.setSlug(category.getSlug() + "-deleted-" + System.currentTimeMillis());
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(savedCategory);
    }
}
