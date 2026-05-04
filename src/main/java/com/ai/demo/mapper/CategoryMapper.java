package com.ai.demo.mapper;

import com.ai.demo.dto.request.CategoryRequest;
import com.ai.demo.dto.response.CategoryResponse;
import com.ai.demo.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //1. Request DTO to Entity
    Category toCategory(CategoryRequest request);

    //2.Entity to Response DTO
    CategoryResponse toCategoryResponse(Category category);

    //3. Update Entity from Request DTO
    void updateCategory(@MappingTarget Category category, CategoryRequest request);
}
