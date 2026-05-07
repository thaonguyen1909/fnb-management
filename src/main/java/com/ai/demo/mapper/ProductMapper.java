package com.ai.demo.mapper;

import com.ai.demo.dto.request.ProductRequest;
import com.ai.demo.dto.response.ProductDetailResponse;
import com.ai.demo.dto.response.ProductSummaryResponse;
import com.ai.demo.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper {
    //Tính chất tàng hình( ko để mapstruct ghi đè các dữ liệu null vào các biến)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "optionGroup", ignore = true)
    @Mapping(target = "isAvailable",defaultValue = "true")
    @Mapping(target = "isCustomize", defaultValue = "false")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Product toEntity(ProductRequest request);

    //Tính chất bắc cầu (product ko có field categoryName nên từ category.name -> categoryName)
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductSummaryResponse toSummaryResponse(Product product);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    ProductDetailResponse toDetailResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "optionGroup", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateProductFromRequest(@MappingTarget Product product, ProductRequest request);
}
