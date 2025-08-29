package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.EmployeeSearchRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.EmployeeResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Employee;
import com.huynguyen.bbqrestaurantmanagement.service.EmployeeService;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController extends GenericController<Employee, Long> {

    private final EmployeeService employeeService;

    protected EmployeeController(GenericService<Employee, Long> genericService, EmployeeService employeeService) {
        super(genericService);
        this.employeeService = employeeService;
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<EmployeeResponse>> searchByName(@RequestBody EmployeeSearchRequest request) {
        List<EmployeeResponse> result = employeeService.searchByName(request.getEmployeeName());

        return ApiResponse
                .<List<EmployeeResponse>>builder()
                .result(result)
                .build();
    }
}
