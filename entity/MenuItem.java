package com.huynguyen.bbqrestaurantmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.huynguyen.bbqrestaurantmanagement.enums.MenuItemCategory;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(nullable = false, unique = true, name = "menu_item_name")
    String menuItemName;

    @Column(nullable = false, precision = 10, scale = 2, name = "price")
    BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "category")
    MenuItemCategory category;

    @OneToMany(mappedBy = "menuItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference("ingredient-menuItem-ref")
    Set<MenuItemIngredient> menuItemIngredients;
}
