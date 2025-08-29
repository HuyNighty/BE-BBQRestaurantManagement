package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.MenuItemRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.MenuItemResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.MenuItem;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;
import com.huynguyen.bbqrestaurantmanagement.service.MenuItemService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menuItems")
@PreAuthorize("hasRole('ADMIN')")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuItemController extends GenericController<MenuItem, Long> {

    MenuItemService menuItemService;

    protected MenuItemController(GenericService<MenuItem, Long> genericService, MenuItemService menuItemService) {
        super(genericService);
        this.menuItemService = menuItemService;
    }

    @PostMapping("/search/greater-than")
    public ApiResponse<List<MenuItemResponse>> searchByPriceGreaterThan(@RequestBody MenuItemRequest request) {
        return ApiResponse
                .<List<MenuItemResponse>>builder()
                .result(menuItemService.searchByPriceGreaterThan(request.getPrice()))
                .build();
    }

    @PostMapping("/search/less-than")
    public ApiResponse<List<MenuItemResponse>> searchByPriceLessThanEqual(@RequestBody MenuItemRequest request) {
        return ApiResponse
                .<List<MenuItemResponse>>builder()
                .result(menuItemService.searchByPriceLessThanEqual(request.getPrice()))
                .build();
    }

    @PostMapping("/search")
    public ApiResponse<List<MenuItemResponse>> searchByPMenuItems(@RequestBody MenuItemRequest request) {
        return ApiResponse
                .<List<MenuItemResponse>>builder()
                .result(menuItemService.searchMenuItems(request.getMenuItemName(), request.getPrice()))
                .build();
    }

    @PostMapping("/search-by-category")
    public ApiResponse<List<MenuItemResponse>> searchByCategory(@RequestBody MenuItemRequest request) {
        return ApiResponse
                .<List<MenuItemResponse>>builder()
                .result(menuItemService.searchByCategory(request.getCategory()))
                .build();
    }
}
