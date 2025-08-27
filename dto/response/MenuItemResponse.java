package com.huynguyen.bbqrestaurantmanagement.dto.response;

import com.huynguyen.bbqrestaurantmanagement.enums.MenuItemCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {

    Long menuItemId;
    String menuItemName;
    BigDecimal price;
    String category;
}
