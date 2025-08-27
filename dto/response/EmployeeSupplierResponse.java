package com.huynguyen.bbqrestaurantmanagement.dto.response;

import com.huynguyen.bbqrestaurantmanagement.enums.BookingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeSupplierResponse {
    Long employeeId;
    String employeeName;

    Long supplierId;
    String supplierName;

    LocalDateTime orderTime;
    BigDecimal totalOrderAmount;
    BookingStatus bookingStatus;
}
