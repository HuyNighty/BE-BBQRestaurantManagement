package com.huynguyen.bbqrestaurantmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.huynguyen.bbqrestaurantmanagement.keys.MenuItemIngredientId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "menu_item_ingredients")
public class MenuItemIngredient {

    @EmbeddedId
    MenuItemIngredientId menuItemIngredientId;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    @MapsId("menuItemId")
    @JsonBackReference("ingredient-menuItem-ref")
    MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    @MapsId("ingredientId")
    @JsonBackReference("menu-ingredient-ref")
    Ingredient ingredient;

    @Column(name = "quantity_required", nullable = false)
    Integer quantityRequired;
}
