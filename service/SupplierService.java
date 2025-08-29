package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.request.SupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.SupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.SupplierWithIngredientsAndEmployeesResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Supplier;

import java.util.List;

public interface SupplierService extends GenericService<Supplier, Long> {

    List<SupplierWithIngredientsAndEmployeesResponse> searchSupplierBySupplierName(String keyword);
    List<SupplierWithIngredientsAndEmployeesResponse> searchSupplierByContactPerson(String personName);
    List<SupplierWithIngredientsAndEmployeesResponse> getAllSuppliersWithDetails();
    void delete(Long id);
    SupplierResponse create(SupplierRequest request);
}
