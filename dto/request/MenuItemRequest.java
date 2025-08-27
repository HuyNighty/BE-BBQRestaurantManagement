package com.huynguyen.bbqrestaurantmanagement.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemRequest {

    String menuItemName;
    BigDecimal price;
    String category;

}
