package com.huynguyen.bbqrestaurantmanagement.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huynguyen.bbqrestaurantmanagement.enums.BookingStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSupplierRequest {

    Long employeeId;
    Long supplierId;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime orderTime;
    BookingStatus bookingStatus;
    BigDecimal totalOrderAmount;
}
