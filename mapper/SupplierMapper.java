package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.request.SupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.SupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.SupplierWithIngredientsAndEmployeesResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Supplier;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IngredientSupplierMapper.class, EmployeeSupplierMapper.class})
public interface SupplierMapper {

    Supplier toSupplier(SupplierRequest request);

    SupplierResponse toSupplierResponse(Supplier request);

    List<SupplierResponse> toSupplierResponseList(List<Supplier> requests);

    @Mapping(source = "ingredientSuppliers", target = "ingredientSuppliers")
    @Mapping(source = "employeeSuppliers", target = "employeeSuppliers")
    SupplierWithIngredientsAndEmployeesResponse toResponse(Supplier supplier);

    List<SupplierWithIngredientsAndEmployeesResponse> toResponseList(List<Supplier> suppliers);
}
