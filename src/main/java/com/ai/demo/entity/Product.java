package com.ai.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    Category category;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "slug", nullable = false, length = 150)
    String slug;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "is_available", nullable = false, columnDefinition = "boolean default true")
    Boolean isAvailable = true;

    @Column(name = "is_customize", nullable = false, columnDefinition = "boolean default true")
    Boolean isCustomize = false;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductVariant> variants;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<OptionGroup> optionGroup;

}
