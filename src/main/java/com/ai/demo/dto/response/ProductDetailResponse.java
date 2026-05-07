package com.ai.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
    UUID id;
    String name;
    String slug;
    String description;
    String imageUrl;
    Boolean isAvailable;
    Boolean isCustomize;

    UUID categoryId;
    String categoryName;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
