package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.IngredientSupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.IngredientSupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.service.IngredientSupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient-suppliers")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientSupplierController {

    IngredientSupplierService ingredientSupplierService;

    @PostMapping
    public ApiResponse<IngredientSupplierResponse> create(@RequestBody IngredientSupplierRequest request) {
        return ApiResponse
                .<IngredientSupplierResponse>builder()
                .result(ingredientSupplierService.create(request))
                .build();
    }

    @GetMapping("/by-supplier/{supplierId}")
    public ApiResponse<List<IngredientSupplierResponse>> getBySupplier(@PathVariable @Validated Long supplierId) {
        return ApiResponse
                .<List<IngredientSupplierResponse>>builder()
                .result(ingredientSupplierService.getAllBySupplier(supplierId))
                .build();
    }

    @GetMapping("/by-ingredient/{ingredientId}")
    public ApiResponse<List<IngredientSupplierResponse>> getByIngredient(@PathVariable Long ingredientId) {
        return ApiResponse
                .<List<IngredientSupplierResponse>>builder()
                .result(ingredientSupplierService.getAllByIngredient(ingredientId))
                .build();
    }
}
