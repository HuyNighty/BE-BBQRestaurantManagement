package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.MenuItemNameRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.OrderDetailRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.UpdateOrderDetailRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.OrderDetailResponse;
import com.huynguyen.bbqrestaurantmanagement.service.OrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/orderDetails")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderDetailController {

    OrderDetailService orderDetailService;

    @PostMapping
    public ApiResponse<OrderDetailResponse> create(@RequestBody OrderDetailRequest request) {
        return ApiResponse
                .<OrderDetailResponse>builder()
                .result(orderDetailService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<OrderDetailResponse>> getAll() {
        return ApiResponse
                .<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getAll())
                .build();
    }

    @PutMapping("/{orderDetailId}")
    public ApiResponse<OrderDetailResponse> update(@PathVariable Long orderDetailId, @RequestBody UpdateOrderDetailRequest request) {
        return ApiResponse
                .<OrderDetailResponse>builder()
                .result(orderDetailService.update(orderDetailId, request))
                .build();
    }

    @DeleteMapping("/{orderDetailId}")
    public ApiResponse<Object> delete(@PathVariable Long orderDetailId) {
        orderDetailService.delete(orderDetailId);
        return ApiResponse
                .builder()
                .code(1000)
                .message("success")
                .build();

    }

    @GetMapping("/{orderDetailId}")
    public ApiResponse<OrderDetailResponse> getById(@PathVariable Long orderDetailId) {
        return ApiResponse
                .<OrderDetailResponse>builder()
                .result(orderDetailService.getById(orderDetailId))
                .build();
    }


    @PostMapping("/searchByMenuItemName")
    public ApiResponse<List<OrderDetailResponse>> getOrderDetailByMenuItemName(@RequestBody MenuItemNameRequest request) {
        return ApiResponse
                .<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getOrderDetailsByMenuItemName(request.getMenuItemName()))
                .build();
    }
}
