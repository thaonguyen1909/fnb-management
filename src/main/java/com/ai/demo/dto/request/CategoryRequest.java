package com.ai.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    @NotBlank(message = "NOT_BLANK")
    @Size(max = 100, message="CATEGORY_NAME_SIZE")
    String name;

    Integer displayOrder;

    Boolean isActive;
}
