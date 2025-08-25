package com.huynguyen.bbqrestaurantmanagement.repository;

import com.huynguyen.bbqrestaurantmanagement.entity.MenuItemIngredient;
import com.huynguyen.bbqrestaurantmanagement.keys.MenuItemIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemIngredientRepository extends JpaRepository<MenuItemIngredient, MenuItemIngredientId> {

    List<MenuItemIngredient> findByMenuItem_MenuItemId(Long menuItemId);
    List<MenuItemIngredient> findByIngredient_IngredientId(Long ingredientId);
}
