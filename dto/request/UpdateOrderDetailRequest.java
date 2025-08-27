package com.huynguyen.bbqrestaurantmanagement.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateOrderDetailRequest {

    Long menuItemId;
    Integer quantity;
    BigDecimal unitPrice;
}
