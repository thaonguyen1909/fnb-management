package com.ai.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSummaryResponse {
    UUID id;
    String name;
    String imageUrl;
    Boolean isAvailable;

    UUID categoryId;
    String categoryName;
}
