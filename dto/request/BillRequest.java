package com.huynguyen.bbqrestaurantmanagement.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillRequest {

    BigDecimal subTotalAmount;
    LocalDateTime paymentTime;
    BigDecimal taxAmount;
    BigDecimal grandTotalAmount;
}
