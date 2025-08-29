package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.request.IngredientRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.IngredientResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Ingredient;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.mapper.IngredientMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.IngredientRepository;
import com.huynguyen.bbqrestaurantmanagement.service.IngredientService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientServiceImpl extends GenericServiceImpl<Ingredient, Long> implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientServiceImpl(JpaRepository<Ingredient, Long> jpaRepository, IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        super(jpaRepository);
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }
    @Override
    public List<IngredientResponse> searchByIngredientName(String keyword) {
        List<Ingredient> ingredients = ingredientRepository.findByIngredientNameContainingIgnoreCase(keyword);

        if (ingredients.isEmpty()) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return ingredientMapper.toIngredientResponseList(ingredients);
    }

    @Override
    public IngredientResponse createIngredient(IngredientRequest request) {

        Ingredient ingredient = Ingredient
                .builder()
                .ingredientName(request.getIngredientName())
                .unit(request.getUnit())
                .build();

        ingredientRepository.save(ingredient);

        return ingredientMapper.toIngredientResponse(ingredient);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        if (ingredient.getIngredientSupplier() != null) {
            ingredient.getIngredientSupplier().clear();
        }

        ingredientRepository.delete(ingredient);
    }
}
