package com.ai.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@SQLRestriction("is_deleted = false")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "slug", nullable = false, length = 100)
    String slug;

    @Column(name = "display_order", nullable = false)
    Integer displayOrder = 0;

    @Column(name = "is_active", nullable = false)
    Boolean isActive = true;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Product> products;
}
