package com.ai.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "option_items")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OptionItem extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id", nullable = false)
    OptionGroup optionGroup;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "extra_price", nullable = false, precision = 10, scale = 0)
    @Builder.Default
    BigDecimal extraPrice = BigDecimal.ZERO;

    @Column(name = "is_default", nullable = false)
    @Builder.Default
    Boolean isDefault = false;

}
