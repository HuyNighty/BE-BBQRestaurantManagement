package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.MenuItemIngredientResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.MenuItemIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MenuItemIngredientMapper {

    @Mapping(source = "ingredient.ingredientId", target = "ingredientId")
    @Mapping(source = "ingredient.ingredientName", target = "ingredientName")
    @Mapping(source = "ingredient.unit", target = "unit")
    @Mapping(source = "menuItem.menuItemId", target = "menuItemId")
    @Mapping(source = "menuItem.menuItemName", target = "menuItemName")
    @Mapping(source = "menuItem.price", target = "price")
    @Mapping(source = "menuItem.category", target = "category")
    @Mapping(source = "quantityRequired", target = "quantityRequired")
    MenuItemIngredientResponse toResponse(MenuItemIngredient menuItemIngredient);

    Set<MenuItemIngredientResponse> tResponseSet(Set<MenuItemIngredient> menuItemIngredients);
}
