package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.IngredientRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.IngredientSearchRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.IngredientResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Ingredient;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;
import com.huynguyen.bbqrestaurantmanagement.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/ingredients")
public class IngredientController extends GenericController<Ingredient, Long>{

    private final IngredientService ingredientService;

    protected IngredientController(GenericService<Ingredient, Long> genericService, IngredientService ingredientService) {
        super(genericService);
        this.ingredientService = ingredientService;
    }

    @PostMapping("/search")
    public ApiResponse<List<IngredientResponse>> searchByIngredientName(@RequestBody IngredientSearchRequest request) {
        List<IngredientResponse> ingredients = ingredientService.searchByIngredientName(request.getIngredientName());

        return ApiResponse
                .<List<IngredientResponse>>builder()
                .result(ingredients)
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ApiResponse<IngredientResponse> create(@RequestBody IngredientRequest request) {
        IngredientResponse response = ingredientService.createIngredient(request);
        return ApiResponse
                .<IngredientResponse>builder()
                .result(response)
                .build();
    }

    @DeleteMapping("/{id}")
    @Override
    public ApiResponse<Object> delete(@PathVariable Long id) {
        ingredientService.delete(id);

        return ApiResponse
                .<Object>builder()
                .result("Ingredient with ID " + id + " deleted successfully.")
                .build();
    }

}
