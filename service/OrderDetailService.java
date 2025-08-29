package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.request.OrderDetailRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.UpdateOrderDetailRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {

    OrderDetailResponse create(OrderDetailRequest request);
    List<OrderDetailResponse> getAll();
    OrderDetailResponse getById(Long orderDetailId);
    OrderDetailResponse update(Long orderDetailId, UpdateOrderDetailRequest request);
    void delete(Long orderDetailId);
    List<OrderDetailResponse> getOrderDetailsByMenuItemName(String menuItemName);
}
