package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.request.IngredientSupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.IngredientSupplierResponse;

import java.util.List;

public interface IngredientSupplierService {
    IngredientSupplierResponse create(IngredientSupplierRequest request);

    List<IngredientSupplierResponse> getAllBySupplier(Long supplierId);
    List<IngredientSupplierResponse> getAllByIngredient(Long ingredientId);
}
