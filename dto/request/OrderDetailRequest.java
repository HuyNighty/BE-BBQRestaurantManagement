package com.huynguyen.bbqrestaurantmanagement.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailRequest {

    Long orderId;
    Long menuItemId;
    Integer quantity;
    BigDecimal priceAtOrder;
}
