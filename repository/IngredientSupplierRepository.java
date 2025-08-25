package com.huynguyen.bbqrestaurantmanagement.repository;

import com.huynguyen.bbqrestaurantmanagement.entity.IngredientSupplier;
import com.huynguyen.bbqrestaurantmanagement.keys.IngredientSupplierId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientSupplierRepository extends JpaRepository<IngredientSupplier, IngredientSupplierId> {

    List<IngredientSupplier> findBySupplier_SupplierId(Long supplierId);
    List<IngredientSupplier> findByIngredient_ingredientId(Long ingredientId);
}
