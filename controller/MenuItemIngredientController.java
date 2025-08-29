package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.MenuItemIngredientRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.MenuItemIngredientResponse;
import com.huynguyen.bbqrestaurantmanagement.service.MenuItemIngredientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/menuItem-ingredients")
@PreAuthorize("hasRole('ADMIN')")
public class MenuItemIngredientController {

    MenuItemIngredientService menuItemIngredientService;

    @PostMapping
    public ApiResponse<MenuItemIngredientResponse> create(@RequestBody @Validated MenuItemIngredientRequest request) {
        return ApiResponse
                .<MenuItemIngredientResponse>builder()
                .result(menuItemIngredientService.create(request))
                .build();
    }

    @GetMapping("/by-menuItemId/{menuItemId}")
    public ApiResponse<List<MenuItemIngredientResponse>> searchByMenuItemId(@PathVariable Long menuItemId) {
        return ApiResponse
                .<List<MenuItemIngredientResponse>>builder()
                .result(menuItemIngredientService.getByMenuItemId(menuItemId))
                .build();
    }

    @GetMapping("by-ingredientId/{ingredientId}")
    public ApiResponse<List<MenuItemIngredientResponse>> searchByIngredientId(@PathVariable Long ingredientId) {
        return ApiResponse
                .<List<MenuItemIngredientResponse>>builder()
                .result(menuItemIngredientService.getByIngredientId(ingredientId))
                .build();
    }

}
