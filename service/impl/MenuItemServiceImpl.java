package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.response.MenuItemResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.MenuItem;
import com.huynguyen.bbqrestaurantmanagement.enums.MenuItemCategory;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.mapper.MenuItemMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.MenuItemRepository;
import com.huynguyen.bbqrestaurantmanagement.service.MenuItemService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuItemServiceImpl extends GenericServiceImpl<MenuItem, Long> implements MenuItemService {

    MenuItemRepository menuItemRepository;
    MenuItemMapper menuItemMapper;

    public MenuItemServiceImpl(JpaRepository<MenuItem, Long> jpaRepository, MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
        super(jpaRepository);
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Override
    public List<MenuItemResponse> searchMenuItems(String name, BigDecimal price) {
        List<MenuItem> menuItems;

        if (name != null && price != null) {
            menuItems = menuItemRepository.findByMenuItemNameContainingIgnoreCaseAndPrice(name, price);
        } else if (name != null) {
            menuItems = menuItemRepository.findByMenuItemNameContainingIgnoreCase(name);
        } else if (price != null) {
            menuItems = menuItemRepository.findByPrice(price);
        } else {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        if (menuItems.isEmpty()) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return menuItemMapper.toResponseList(menuItems);
    }


    @Override
    public List<MenuItemResponse> searchByPriceGreaterThan(BigDecimal price) {
        List<MenuItem> menuItems = menuItemRepository.findByPriceGreaterThan(price);

        if (menuItems.isEmpty()) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return menuItemMapper.toResponseList(menuItems);    }

    @Override
    public List<MenuItemResponse> searchByPriceLessThanEqual(BigDecimal price) {
        List<MenuItem> menuItems = menuItemRepository.findByPriceLessThanEqual(price);

        if (menuItems.isEmpty()) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return menuItemMapper.toResponseList(menuItems);    }

    @Override
    public List<MenuItemResponse> searchByCategory(String keyword) {
        String normalized = keyword.trim().toLowerCase();

        List<MenuItemCategory> matchedCategories = Arrays.stream(MenuItemCategory.values())
                .filter(category -> category.name().toLowerCase().contains(normalized))
                .toList();

        if (matchedCategories.isEmpty()) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        List<MenuItem> menuItems = menuItemRepository.findByCategoryIn(matchedCategories);

        if (menuItems.isEmpty()) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return menuItemMapper.toResponseList(menuItems);
    }

    @Override
    @Transactional
    public MenuItem save(MenuItem menuItem) {

        if (menuItem.getMenuItemId() == null) {
            if (menuItemRepository.findByMenuItemName(menuItem.getMenuItemName()).isPresent()) {
                throw new AppException(ErrorCode.DUPLICATE_RESOURCE);
            }
            return jpaRepository.save(menuItem);
        } else {

            MenuItem existingMenuItem = jpaRepository.findById(menuItem.getMenuItemId())
                    .orElseThrow(() -> {
                        return new AppException(ErrorCode.ENTITY_NOT_FOUND);
                    });

            menuItemRepository.findByMenuItemName(menuItem.getMenuItemName()).ifPresent(itemWithSameName -> {
                if (!itemWithSameName.getMenuItemId().equals(menuItem.getMenuItemId())) {
                    throw new AppException(ErrorCode.DUPLICATE_RESOURCE);
                }
            });


            copyNonNullProperties(menuItem, existingMenuItem);

            return jpaRepository.save(existingMenuItem);
        }
    }
}
