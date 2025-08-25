package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.OrderDetailResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", imports = BigDecimal.class)
public interface OrderDetailMapper {

    @Mapping(source = "orderDetailId", target = "orderDetailId")
    @Mapping(source = "menuItem.menuItemName", target = "menuItemName")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "priceAtOrder", target = "unitPrice")
    @Mapping(expression = "java(orderDetail.getPriceAtOrder().multiply(BigDecimal.valueOf(orderDetail.getQuantity())))", target = "totalPrice")
    OrderDetailResponse toResponse(OrderDetail orderDetail);
}
