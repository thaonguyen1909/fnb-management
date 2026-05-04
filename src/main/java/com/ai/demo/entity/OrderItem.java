package com.ai.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "order_items")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id", nullable = false)
    ProductVariant productVariant;

    @Column(name = "quantity", nullable = false)
    Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 0)
    BigDecimal unitPrice;

    @Column(name = "total_price", nullable = false, precision = 12, scale = 0)
    BigDecimal totalPrice;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderItemOption> selectedOptions;




}
