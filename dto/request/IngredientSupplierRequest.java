package com.huynguyen.bbqrestaurantmanagement.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientSupplierRequest {

    Long ingredientId;
    Long supplierId;
    Double price;
    Integer deliveryTimeEstimate;
}
