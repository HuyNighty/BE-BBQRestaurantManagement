package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.response.EmployeeResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Employee;

import java.util.List;

public interface EmployeeService extends GenericService<Employee, Long>{
    List<EmployeeResponse> searchByName(String keyword);
}
