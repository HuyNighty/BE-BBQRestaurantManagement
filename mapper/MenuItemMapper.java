package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.MenuItemResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    @Mapping(source = "menuItemId", target = "menuItemId")
    @Mapping(source = "menuItemName", target = "menuItemName")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "category", target = "category")
    MenuItemResponse toResponse(MenuItem menuItem);

    List<MenuItemResponse> toResponseList(List<MenuItem> menuItems);
}
