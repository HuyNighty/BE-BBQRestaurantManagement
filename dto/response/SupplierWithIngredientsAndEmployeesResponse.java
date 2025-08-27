package com.huynguyen.bbqrestaurantmanagement.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierWithIngredientsAndEmployeesResponse {

    Long supplierId;
    String supplierName;
    String contactPerson;
    String phoneNumber;
    String email;
    String address;

    Set<IngredientSupplierResponse> ingredientSuppliers;
    Set<EmployeeSupplierResponse> employeeSuppliers;
}
