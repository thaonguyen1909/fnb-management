package com.ai.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payments")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false, length = 20)
    PaymnentMethod PaymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 20)
    @Builder.Default
    PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "amount", nullable = false, precision = 12, scale = 0)
    BigDecimal amount;

    @Column(name = "transactional_ref", length = 100)
    String transactionalRef;

    @Column(name = "paid_at")
    LocalDateTime paidAt;

    public enum PaymnentMethod{
        CASH, BANK_TRANSFER, MOMO, VNPAY, ZALO_PAY
    }

    public enum PaymentStatus {
        PENDING, SUCCESS, FAILED, REFUNDED
    }
}
