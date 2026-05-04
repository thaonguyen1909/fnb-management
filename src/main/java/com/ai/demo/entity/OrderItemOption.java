package com.ai.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item_options")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemOption extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_item_id", nullable = false)
    OptionItem optionItem;

    @Column(name = "extra_price", nullable = false, precision = 10, scale = 0)
    BigDecimal extraPrice;
}
