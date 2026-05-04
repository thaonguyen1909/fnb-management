package com.ai.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    UUID id;
    String name;
    Integer displayOrder;
    Boolean isActive;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
}
