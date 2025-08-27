package com.huynguyen.bbqrestaurantmanagement.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {

    Long orderDetailId;
    String menuItemName;
    Integer quantity;
    BigDecimal unitPrice; // = priceAtOrder.
    BigDecimal totalPrice;
}
