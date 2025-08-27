package com.huynguyen.bbqrestaurantmanagement.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientRequest {
    String ingredientName;
    String unit;
}