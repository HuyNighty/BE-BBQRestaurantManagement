package com.huynguyen.bbqrestaurantmanagement.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemIngredientRequest {

    Long ingredientId;
    Long menuItemId;
    Integer quantityRequired;
}
