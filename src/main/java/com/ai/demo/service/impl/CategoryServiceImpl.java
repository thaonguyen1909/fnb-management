package com.ai.demo.service.impl;

import com.ai.demo.dto.request.CategoryRequest;
import com.ai.demo.dto.response.CategoryDeleteResponse;
import com.ai.demo.dto.response.CategoryDetailResponse;
import com.ai.demo.dto.response.CategoryResponse;
import com.ai.demo.dto.response.CategorySummaryResponse;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;


    @Override
    public CategoryDetailResponse createCategory(CategoryRequest request) {
        String generatedSlug = StringUtils.toSlug(request.getName());

        if(categoryRepository.existsBySlug(generatedSlug)){
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        Category category = categoryMapper.toEntity(request);
        category.setSlug(generatedSlug);

        if (category.getDisplayOrder() == null) category.setDisplayOrder(0);
        if (category.getIsActive() == null) category.setIsActive(true);


        Category saved = categoryRepository.save(category);
        return categoryMapper.toDetailResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorySummaryResponse> getActiveCategories() {
        return categoryRepository.findAllByIsActiveTrueOrderByDisplayOrderAsc()
                .stream()
                .map(categoryMapper::toSummaryResponse)
                .toList();
    }

    @Override
    public List<CategorySummaryResponse> getAllCategories() {
        return categoryRepository.findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(categoryMapper::toSummaryResponse)
                .toList();
    }

    @Override
    public CategoryDetailResponse getCategoryById(UUID id) {
        Category category = findByIdOrThrow(id);
        return categoryMapper.toDetailResponse(category);
    }


    @Override
    @Transactional
    public CategoryDetailResponse updateCategory(UUID id, CategoryRequest request) {
        Category category = findByIdOrThrow(id);

        String newSlug = StringUtils.toSlug(request.getName());

        if(!category.getSlug().equals(newSlug) && categoryRepository.existsBySlug(newSlug)){
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        categoryMapper.updateCategoryFromRequest(category, request);
        category.setSlug(newSlug);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toDetailResponse(saved);
    }

    @Override
    @Transactional
    public CategoryDeleteResponse deleteCategory(UUID id) {
        Category category = findByIdOrThrow(id);

        category.setDeleted(true);

        category.setSlug(category.getSlug() + "-deleted-" + System.currentTimeMillis());
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toDeleteResponse(savedCategory);
    }

    private Category findByIdOrThrow(UUID id){
        return categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }
}
