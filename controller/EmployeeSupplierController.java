package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.EmployeeSupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.EmployeeSupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.service.EmployeeSupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/employee-suppliers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeSupplierController {

    EmployeeSupplierService employeeSupplierService;

    @PostMapping
    public ApiResponse<EmployeeSupplierResponse> create(@RequestBody @Validated EmployeeSupplierRequest request) {
        return ApiResponse
                .<EmployeeSupplierResponse>builder()
                .result(employeeSupplierService.create(request))
                .build();
    }

    @GetMapping("/by-employeeId/{employeeId}")
    public ApiResponse<List<EmployeeSupplierResponse>> searchByEmployeeId(@PathVariable Long employeeId) {
        return ApiResponse
                .<List<EmployeeSupplierResponse>>builder()
                .result(employeeSupplierService.searchByEmployeeId(employeeId))
                .build();
    }

    @GetMapping("/by-supplierId/{supplierId}")
    public ApiResponse<List<EmployeeSupplierResponse>> searchBySupplierId(@PathVariable Long supplierId) {
        return ApiResponse
                .<List<EmployeeSupplierResponse>>builder()
                .result(employeeSupplierService.searchBySupplierId(supplierId))
                .build();
    }
}
