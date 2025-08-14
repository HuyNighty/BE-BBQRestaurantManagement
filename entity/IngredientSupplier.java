package com.huynguyen.bbqrestaurantmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.huynguyen.bbqrestaurantmanagement.keys.IngredientSupplierId;
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
@Table(name = "ingredient_suppliers")
public class IngredientSupplier {

    @EmbeddedId
    IngredientSupplierId ingredientSupplierId;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false, updatable = false)
    @MapsId("ingredientId")
    @JsonBackReference("ingredient-ref")
    Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false, updatable = false)
    @MapsId("supplierId")
    @JsonBackReference("supplier-ref")
    Supplier supplier;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "delivery_time_estimate")
    Integer deliveryTimeEstimate;
}
