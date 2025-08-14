package com.huynguyen.bbqrestaurantmanagement.entity;

import com.huynguyen.bbqrestaurantmanagement.enums.BookingStatus;
import com.huynguyen.bbqrestaurantmanagement.keys.PromotionMenuItemId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "promotion_menu_items")
public class PromotionMenuItem {

    @EmbeddedId
    PromotionMenuItemId promotionMenuItemId;

    @ManyToOne
    @JoinColumn(name = "promotion_id", nullable = false)
    @MapsId("promotionId")
    Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "menuItem_id", nullable = false)
    @MapsId("menuItemId")
    MenuItem menuItem;

    @Column(name = "priority", nullable = false)
    Integer priority;

    String note;
}
