package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.EmployeeSupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.EmployeeSupplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface EmployeeSupplierMapper {

    @Mapping(source = "employee.employeeId", target = "employeeId")
    @Mapping(source = "employee.employeeName", target = "employeeName")
    @Mapping(source = "supplier.supplierId", target = "supplierId")
    @Mapping(source = "supplier.supplierName", target = "supplierName")
    @Mapping(source = "orderTime", target = "orderTime")
    @Mapping(source = "bookingStatus", target = "bookingStatus")
    @Mapping(source = "totalOrderAmount", target = "totalOrderAmount")
    EmployeeSupplierResponse toResponse(EmployeeSupplier employeeSupplier);

    Set<EmployeeSupplierResponse> tResponseList(Set<EmployeeSupplier> list);
}
