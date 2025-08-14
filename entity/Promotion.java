package com.huynguyen.bbqrestaurantmanagement.entity;

import com.huynguyen.bbqrestaurantmanagement.enums.PromotionAppliesTo;
import com.huynguyen.bbqrestaurantmanagement.enums.PromotionType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    Long promotionId;

    @Column(name = "code", unique = true, nullable = false)
    String code; // Mã code của khuyến mãi (ví dụ: "SUMMER20", "GIAM20K")

    @Column(name = "description")
    String description; // Mô tả chi tiết về khuyến mãi (ví dụ: "Giảm 20% cho hóa đơn trên 500K")

    @Column(name = "start_date", nullable = false)
    LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    boolean isActive = true; // Trạng thái hoạt động của khuyến mãi (true = đang hoạt động, false = đã dừng/hết hạn)

    @Enumerated(EnumType.STRING)
    @Column(name = "applies_to") // Áp dụng cho (ví dụ: ENTIRE_BILL, SPECIFIC_ITEM, CATEGORY)
    PromotionAppliesTo appliesTo; // Kiểu áp dụng của khuyến mãi (áp dụng cho toàn hóa đơn, món cụ thể, danh mục,...)

    @Enumerated(EnumType.STRING) // Lưu dưới dạng chuỗi trong DB
    @Column(name = "promotion_type", nullable = false)
    PromotionType promotionType;

    @Column(name = "discount_value", precision = 10, scale = 2)
    BigDecimal discountValue;

    @Column(name = "minimum_order_amount", precision = 10, scale = 2)
    BigDecimal minimumOrderAmount; // Ngưỡng tổng tiền hóa đơn tối thiểu để khuyến mãi có hiệu lực
    // Có thể null nếu không có ngưỡng tối thiểu.
}
