package com.huynguyen.bbqrestaurantmanagement.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "bills")
public class Bill { // Có thuế (theo Coop Mart).

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    Long billId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    Order order;

    @Column(nullable = false, precision = 10, scale = 2, name = "sub_total_amount")
    BigDecimal subTotalAmount;

    @Column(nullable = false, name = "payment_time")
    LocalDateTime paymentTime;

    @Column(nullable = false, precision = 10, scale = 2, name = "tax_amount")
    BigDecimal taxAmount;

    @Column(nullable = false, precision = 10, scale = 2, name = "grand_total_amount")
    BigDecimal grandTotalAmount;
}
