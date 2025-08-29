package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.response.EmployeeResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Employee;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.mapper.EmployeeMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.EmployeeRepository;
import com.huynguyen.bbqrestaurantmanagement.service.EmployeeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, Long> implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(JpaRepository<Employee, Long> jpaRepository, EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        super(jpaRepository);
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeResponse> searchByName(String keyword) {
        List<Employee> employees = employeeRepository.findByEmployeeNameContainingIgnoreCase(keyword);

        if (employees.isEmpty()) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return employees
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }
}
