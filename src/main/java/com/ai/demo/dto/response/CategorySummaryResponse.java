package com.ai.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategorySummaryResponse {
    UUID id;
    String name;
    Integer displayOrder;
    Boolean isActive;
}
