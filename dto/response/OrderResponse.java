package com.huynguyen.bbqrestaurantmanagement.dto.response;

import com.huynguyen.bbqrestaurantmanagement.entity.Customer;
import com.huynguyen.bbqrestaurantmanagement.entity.OrderDetail;
import com.huynguyen.bbqrestaurantmanagement.enums.StatusOrder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponse {

    Long orderId;
    String tableName;
    LocalDateTime orderTime;
    StatusOrder statusOrder;
    List<OrderDetail> orderDetails;
    Customer customer;
}
