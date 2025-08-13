package com.huynguyen.bbqrestaurantmanagement.entity;

import com.huynguyen.bbqrestaurantmanagement.keys.BillPaymentMethodId;
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
@Table(name = "bill_payment_method")
public class BillPaymentMethod {

    @EmbeddedId
    BillPaymentMethodId billPaymentMethodId;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    @MapsId("billId")
    Bill bill;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    @MapsId("paymentMethodId")
    PaymentMethod paymentMethod;
}
