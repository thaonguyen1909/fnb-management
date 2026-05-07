package com.ai.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    @NotNull(message = "INVALID_DATA")
    UUID categoryId;

    @NotBlank(message = "NOT_BLANK")
    @Size(max = 100, message= "INVALID_DATA")
    String name;

    String description;
    String imageUrl;
    Boolean isAvailable;
    Boolean isCustomize;
}
