package com.huynguyen.bbqrestaurantmanagement.repository;

import com.huynguyen.bbqrestaurantmanagement.entity.MenuItem;
import com.huynguyen.bbqrestaurantmanagement.enums.MenuItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByMenuItemNameContainingIgnoreCase(String menuItemName);
    List<MenuItem> findByPriceGreaterThan(BigDecimal minPrice);
    List<MenuItem> findByPriceLessThanEqual(BigDecimal maxPrice);
    List<MenuItem> findByPrice(BigDecimal price);
    List<MenuItem> findByMenuItemNameContainingIgnoreCaseAndPrice(String menuItemName, BigDecimal price);
    List<MenuItem> findByCategoryIn(List<MenuItemCategory> categories);
    Optional<MenuItem> findByMenuItemName(String name);

}
