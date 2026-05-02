package com.ai.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
@Table(name = "orders", indexes = {
        @Index(name = "idx_orders_order_code", columnList = "order_code"),
        @Index(name = "idx_orders_status", columnList = "status"),
        @Index(name = "idx_orders_ordered_at", columnList = "ordered_at")
})

public class Order extends BaseEntity {
    @Column(name = "order_code", nullable = false, length = 30)
    String orderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    CafeTable table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    User staff;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    OrderType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    OrderStatus status = OrderStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false, length = 20)
    OrderChannel channel;

    @Column(name = "subtotal", nullable = false, precision = 12, scale = 0)
    BigDecimal subtotal;

    @Column(name = "discount_amount", nullable = false, precision = 12, scale = 0)
    @Builder.Default
    BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 0)
    BigDecimal totalAmount;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    @Column(name = "ordered_at", nullable = false)
    LocalDateTime orderedAt;

    @Column(name = "completed_at")
    LocalDateTime completedAt;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Payment> payments;

    public enum OrderType {
        DINE_IN,
        TAKEAWAY,
        DELIVERY
    }

     public enum OrderStatus {
        PENDING,
        CONFIRMED,
        PREPARING,
        READY,
        COMPLETED,
        CANCELLED
     }

     public enum OrderChannel {
        IN_STORE,
        ONLINE_APP,
        WALK_IN
     }

}
