package com.ai.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Table(name = "tables")
@Entity
public class CafeTable extends BaseEntity{
    @Column(name = "code", nullable = false, length = 10, unique = true)
    String code;

    @Column(name = "capacity", nullable = false)
    Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    TableStatus status = TableStatus.AVAILABLE;

    @Column(name = "qr_code_url")
    String qrCodeUrl;

    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY)
    List<Order> orders;

    public enum TableStatus {
        AVAILABLE,
        OCCUPIED,
        RESERVED,
        UNAVAILABLE
    }
}
