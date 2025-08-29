package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.response.MenuItemResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.MenuItem;
import com.huynguyen.bbqrestaurantmanagement.enums.MenuItemCategory;

import java.math.BigDecimal;
import java.util.List;

public interface MenuItemService extends GenericService<MenuItem, Long>{

    List<MenuItemResponse> searchMenuItems(String name, BigDecimal price);
    List<MenuItemResponse> searchByPriceGreaterThan(BigDecimal price);
    List<MenuItemResponse> searchByPriceLessThanEqual(BigDecimal price);
    List<MenuItemResponse> searchByCategory(String keyword);
}
