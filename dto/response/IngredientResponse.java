package com.huynguyen.bbqrestaurantmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientResponse {

    Long ingredientId;
    String ingredientName;
    String unit;
    List<IngredientSupplierResponse> ingredientSupplierResponses;
}
