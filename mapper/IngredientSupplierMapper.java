package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.IngredientSupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.IngredientSupplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IngredientSupplierMapper {

    @Mapping(source = "ingredient.ingredientId", target = "ingredientId")
    @Mapping(source = "ingredient.ingredientName", target = "ingredientName")

    @Mapping(source = "supplier.supplierId", target = "supplierId")
    @Mapping(source = "supplier.supplierName", target = "supplierName")

    @Mapping(source = "price", target = "price")
    @Mapping(source = "deliveryTimeEstimate", target = "deliveryTimeEstimate")
    IngredientSupplierResponse toResponse(IngredientSupplier ingredientSupplier);

    Set<IngredientSupplierResponse> toResponseList(Set<IngredientSupplier> list);
}
