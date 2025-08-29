package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.request.MenuItemIngredientRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.MenuItemIngredientResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Ingredient;
import com.huynguyen.bbqrestaurantmanagement.entity.MenuItem;
import com.huynguyen.bbqrestaurantmanagement.entity.MenuItemIngredient;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.keys.MenuItemIngredientId;
import com.huynguyen.bbqrestaurantmanagement.mapper.MenuItemIngredientMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.IngredientRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.MenuItemIngredientRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.MenuItemRepository;
import com.huynguyen.bbqrestaurantmanagement.service.MenuItemIngredientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MenuIItemIngredientServiceImpl implements MenuItemIngredientService {

    MenuItemIngredientMapper menuItemIngredientMapper;
    MenuItemIngredientRepository menuItemIngredientRepository;
    MenuItemRepository menuItemRepository;
    IngredientRepository ingredientRepository;

    @Override
    public MenuItemIngredientResponse create(MenuItemIngredientRequest request) {

        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        Ingredient ingredient = ingredientRepository.findById(request.getIngredientId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));


        MenuItemIngredientId id = new MenuItemIngredientId(menuItem.getMenuItemId(), ingredient.getIngredientId());

        MenuItemIngredient menuItemIngredient = MenuItemIngredient
                .builder()
                .menuItemIngredientId(id)
                .ingredient(ingredient)
                .menuItem(menuItem)
                .quantityRequired(request.getQuantityRequired())
                .build();

        menuItemIngredientRepository.save(menuItemIngredient);
        return menuItemIngredientMapper.toResponse(menuItemIngredient);
    }


    @Override
    public List<MenuItemIngredientResponse> getByMenuItemId(Long menuItemId) {
        if (!menuItemRepository.existsById(menuItemId)) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        List<MenuItemIngredient> menuItemIngredients = menuItemIngredientRepository
                .findByMenuItem_MenuItemId(menuItemId);

        if (menuItemIngredients.isEmpty()) {
            throw new AppException(ErrorCode.NO_RELATION_FOUND);
        }

        return menuItemIngredients
                .stream()
                .map(menuItemIngredientMapper::toResponse)
                .toList();
    }

    @Override
    public List<MenuItemIngredientResponse> getByIngredientId(Long ingredientId) {
        if (!ingredientRepository.existsById(ingredientId)) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        List<MenuItemIngredient> menuItemIngredients = menuItemIngredientRepository
                .findByIngredient_IngredientId(ingredientId);

        if (menuItemIngredients.isEmpty()) {
            throw new AppException(ErrorCode.NO_RELATION_FOUND);
        }

        return menuItemIngredients
                .stream()
                .map(menuItemIngredientMapper::toResponse)
                .toList();
    }
}
