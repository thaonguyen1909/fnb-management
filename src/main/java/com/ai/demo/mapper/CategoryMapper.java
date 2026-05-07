package com.ai.demo.mapper;

import com.ai.demo.dto.request.CategoryRequest;
import com.ai.demo.dto.response.CategoryDeleteResponse;
import com.ai.demo.dto.response.CategoryDetailResponse;
import com.ai.demo.dto.response.CategoryResponse;
import com.ai.demo.dto.response.CategorySummaryResponse;
import com.ai.demo.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CategoryMapper {
    //1. Request DTO to Entity
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryRequest request);

    //2.Entity to SummaryResponse
    CategorySummaryResponse toSummaryResponse(Category category);

    //3. Entity -> DetailResponse
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    CategoryDetailResponse toDetailResponse(Category category);

    //4. Update Entity from Request DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // neu co thang nao null thi giu nguyen du lieu cu
    @Mapping(target = "slug", ignore = true)   // slug do Service xử lý
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateCategoryFromRequest(@MappingTarget Category category, CategoryRequest request);

    //5. Entity -> Delete Response
    CategoryDeleteResponse toDeleteResponse(Category category);


}
