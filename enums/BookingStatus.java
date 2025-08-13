package com.huynguyen.bbqrestaurantmanagement.enums;

public enum BookingStatus {
    PENDING,    // Chờ xác nhận
    CONFIRMED,  // Đã xác nhận
    CANCELLED,  // Đã hủy
    COMPLETED,  // Đã hoàn thành (khách đã đến)
    NO_SHOW     // Khách không đến
}
