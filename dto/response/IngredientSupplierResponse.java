package com.huynguyen.bbqrestaurantmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
public class IngredientSupplierResponse {

    Long ingredientId;
    String ingredientName;

    Long supplierId;
    String supplierName;

    Double price;
    Integer deliveryTimeEstimate;
}
