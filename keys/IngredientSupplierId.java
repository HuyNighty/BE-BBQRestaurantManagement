package com.huynguyen.bbqrestaurantmanagement.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class IngredientSupplierId implements Serializable {

    Long ingredientId;
    Long supplierId;
}
