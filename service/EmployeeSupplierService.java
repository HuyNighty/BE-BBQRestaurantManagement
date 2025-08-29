package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.request.EmployeeSupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.EmployeeSupplierResponse;

import java.util.List;

public interface EmployeeSupplierService {

    EmployeeSupplierResponse create(EmployeeSupplierRequest request);
    List<EmployeeSupplierResponse> searchByEmployeeId(Long employeeId);
    List<EmployeeSupplierResponse> searchBySupplierId(Long supplierId);
}
