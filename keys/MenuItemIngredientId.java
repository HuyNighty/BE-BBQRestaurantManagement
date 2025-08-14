package com.huynguyen.bbqrestaurantmanagement.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class MenuItemIngredientId implements Serializable {

    Long menuItemId;
    Long ingredientId;
}
