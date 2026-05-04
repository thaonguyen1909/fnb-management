package com.ai.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "option_groups")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OptionGroup extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    OptionGroupType type;

    @Column(name = "is_required", nullable = false)
    Boolean isRequired = false;

    public enum OptionGroupType {
        SINGLE_SELECT,
        MULTI_SELECT
    }




}
