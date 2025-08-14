package com.huynguyen.bbqrestaurantmanagement.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BillPaymentMethodId implements Serializable {

    Long billId;
    Long paymentMethodId;
}
