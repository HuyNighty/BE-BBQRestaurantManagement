package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.IngredientResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Ingredient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IngredientSupplierMapper.class})
public interface IngredientMapper {
    IngredientResponse toIngredientResponse(Ingredient ingredient);

    List<IngredientResponse> toIngredientResponseList(List<Ingredient> ingredient);
}
