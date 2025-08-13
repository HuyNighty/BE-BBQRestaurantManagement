package com.huynguyen.bbqrestaurantmanagement.entity;

import com.huynguyen.bbqrestaurantmanagement.enums.PaymentMethodName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @Column(name = "payment_method_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long paymentMethodId;

    @Column(name = "method_name", nullable = false)
    PaymentMethodName paymentMethodName;
}
