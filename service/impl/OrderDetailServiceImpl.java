package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.request.OrderDetailRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.UpdateOrderDetailRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.OrderDetailResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.MenuItem;
import com.huynguyen.bbqrestaurantmanagement.entity.Order;
import com.huynguyen.bbqrestaurantmanagement.entity.OrderDetail;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.mapper.OrderDetailMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.MenuItemRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.OrderDetailRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.OrderRepository;
import com.huynguyen.bbqrestaurantmanagement.service.OrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailMapper orderDetailMapper;
    OrderDetailRepository orderDetailRepository;
    OrderRepository orderRepository;
    MenuItemRepository menuItemRepository;

    @Override
    public OrderDetailResponse create(OrderDetailRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        OrderDetail orderDetail = OrderDetail
                .builder()
                .order(order)
                .menuItem(menuItem)
                .quantity(request.getQuantity())
                .priceAtOrder(request.getPriceAtOrder())
                .build();

        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toResponse(orderDetail);
    }

    @Override
    public List<OrderDetailResponse> getAll() {
        return orderDetailRepository
                .findAll()
                .stream()
                .map(orderDetailMapper::toResponse)
                .toList();
    }

    @Override
    public OrderDetailResponse getById(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        return orderDetailMapper.toResponse(orderDetail);
    }

    @Override
    public OrderDetailResponse update(Long orderDetailId, UpdateOrderDetailRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        if (request.getMenuItemId() != null) {
            MenuItem newMenuItem = menuItemRepository.findById(request.getMenuItemId())
                    .orElseThrow(() -> new AppException(ErrorCode.NAME_EXISTED));
            orderDetail.setMenuItem(newMenuItem);
        }

        if (request.getQuantity() != null) {
            orderDetail.setQuantity(request.getQuantity());
        }

        if (request.getUnitPrice() != null) {
            orderDetail.setPriceAtOrder(request.getUnitPrice());
        }

        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toResponse(orderDetail);

    }

    @Override
    public void delete(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        orderDetailRepository.delete(orderDetail);
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailsByMenuItemName(String menuItemName) {
        List<OrderDetail> orderDetail = orderDetailRepository.findByMenuItemName(menuItemName);

        return orderDetail
                .stream()
                .map(orderDetailMapper::toResponse)
                .toList();
    }
}
