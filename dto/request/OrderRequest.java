package com.huynguyen.bbqrestaurantmanagement.dto.request;

import com.huynguyen.bbqrestaurantmanagement.enums.StatusOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {

    Long tableId;
    Long customerId;
    LocalDateTime orderTime;
    StatusOrder statusOrder;
}
