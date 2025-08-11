package com.huynguyen.bbqrestaurantmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.huynguyen.bbqrestaurantmanagement.enums.BookingStatus;
import com.huynguyen.bbqrestaurantmanagement.keys.EmployeeSupplierId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employee_suppliers")
public class EmployeeSupplier {

    @EmbeddedId
    EmployeeSupplierId employeeSupplierId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @MapsId("employeeId")
    @JsonBackReference("employee-to-supplier")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @MapsId("supplierId")
    @JsonBackReference("supplier-to-employee")
    Supplier supplier;

    @Column(name = "order_time", nullable = false)
    LocalDateTime orderTime;

    @Column(name = "booking_status")
    BookingStatus bookingStatus;

    @Column(name = "total_order_amount", precision = 12, scale = 2)
    BigDecimal totalOrderAmount;
}
