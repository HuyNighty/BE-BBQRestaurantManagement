package com.huynguyen.bbqrestaurantmanagement.dto.response;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillResponse {

    Long billId;
    BigDecimal subTotalAmount;
    LocalDateTime paymentTime;
    BigDecimal taxAmount;
    BigDecimal grandTotalAmount;
}
