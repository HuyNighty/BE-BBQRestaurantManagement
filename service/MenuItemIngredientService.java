package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.request.MenuItemIngredientRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.MenuItemIngredientResponse;

import java.util.List;

public interface MenuItemIngredientService {

    MenuItemIngredientResponse create(MenuItemIngredientRequest request);
    List<MenuItemIngredientResponse> getByMenuItemId(Long menuItemId);
    List<MenuItemIngredientResponse> getByIngredientId(Long ingredientId);
}
