package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.request.IngredientRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.IngredientResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Ingredient;

import java.util.List;

public interface IngredientService extends GenericService<Ingredient, Long>{

    List<IngredientResponse> searchByIngredientName(String keyword);
    IngredientResponse createIngredient(IngredientRequest request);
    void delete(Long id);
}
