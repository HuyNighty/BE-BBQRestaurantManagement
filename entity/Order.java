package com.huynguyen.bbqrestaurantmanagement.entity;

import com.huynguyen.bbqrestaurantmanagement.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false) // Một bàn có thể có nhiều Order, nhưng mà 1 Order không thể cho nhiều bàn được.
    RestaurantTable restaurantTable;

    @Column(nullable = false, name = "order_time")
    LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status_order")
    StatusOrder statusOrder;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;
    // Về phần customer, sẽ có nhiều trường hợp sẽ xảy ra với customer:
    // +TH1: Khách đặc bàn onl và không đăng nhập -> Khách vãng lai (null).
    // +TH2: Khách đặc bàn onl và đăng nhập -> có dữ kiện.
    // +TH3: Khách ăn trực tiếp và "không đăng nhập" (Không có thẻ hay những thứ liên quan đến xác thực) -> Khách vãng lai.
    // +TH4: Khách ăn trực tiếp và "có đăng nhập" ( <Authenticated Successfull> Có thẻ hay đại loại vậy để xác thực danh tính).

    //-> Mục đích chính: Nếu người dùng (Khách) có thẻ thì sẽ có một số đặc quyền nhất định như là:
    // Giảm giá, ưu tiên đặt bàn (tùy theo quyền), đại loại vậy...

}
