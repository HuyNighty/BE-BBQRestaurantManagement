package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.EmployeeResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "role", target = "role")
    EmployeeResponse toResponse(Employee employee);
}
