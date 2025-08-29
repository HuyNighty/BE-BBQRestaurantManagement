package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.SupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.SupplierSearchRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.SupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.SupplierWithIngredientsAndEmployeesResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Supplier;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;
import com.huynguyen.bbqrestaurantmanagement.service.SupplierService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/suppliers")
public class SupplierController extends GenericController<Supplier, Long> {

    private final SupplierService supplierService;

    protected SupplierController(GenericService<Supplier, Long> genericService, SupplierService supplierService) {
        super(genericService);
        this.supplierService = supplierService;
    }

    @PostMapping("/searchBySupplierName")
    ApiResponse<List<SupplierWithIngredientsAndEmployeesResponse>> searchBySupplierName(@RequestBody @Validated SupplierSearchRequest request) {
        return ApiResponse
                .<List<SupplierWithIngredientsAndEmployeesResponse>>builder()
                .result(supplierService.searchSupplierBySupplierName(request.getKeyword()))
                .build();
    }

    @PostMapping("/searchByContactPerson")
    ApiResponse<List<SupplierWithIngredientsAndEmployeesResponse>> searchByContactPerson(@RequestBody @Validated SupplierSearchRequest request) {
        return ApiResponse
                .<List<SupplierWithIngredientsAndEmployeesResponse>>builder()
                .result(supplierService.searchSupplierByContactPerson(request.getKeyword()))
                .build();
    }

    @DeleteMapping("/{id}")
    @Override
    public ApiResponse<Object> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ApiResponse.<Object>builder()
                .result("Supplier with ID " + id + " deleted successfully.")
                .build();
    }

    @GetMapping("/getAllDetails")
    public ApiResponse<List<SupplierWithIngredientsAndEmployeesResponse>> getAllDetails() {
        return ApiResponse
                .<List<SupplierWithIngredientsAndEmployeesResponse>>builder()
                .result(supplierService.getAllSuppliersWithDetails())
                .build();
    }

    @PostMapping("/create")
    ApiResponse<SupplierResponse> create(@RequestBody @Validated SupplierRequest request) {
        return ApiResponse
                .<SupplierResponse>builder()
                .result(supplierService.create(request))
                .build();
    }
}
