package com.ai.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CategoryDetailResponse {
    UUID id;
    String name;
    String slug;
    Integer displayOrder;
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
