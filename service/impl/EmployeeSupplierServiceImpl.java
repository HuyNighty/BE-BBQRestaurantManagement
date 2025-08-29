package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.request.EmployeeSupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.EmployeeSupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Employee;
import com.huynguyen.bbqrestaurantmanagement.entity.EmployeeSupplier;
import com.huynguyen.bbqrestaurantmanagement.entity.Supplier;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.keys.EmployeeSupplierId;
import com.huynguyen.bbqrestaurantmanagement.mapper.EmployeeSupplierMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.EmployeeRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.EmployeeSupplierRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.SupplierRepository;
import com.huynguyen.bbqrestaurantmanagement.service.EmployeeSupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeSupplierServiceImpl implements EmployeeSupplierService {

    EmployeeSupplierMapper employeeSupplierMapper;
    EmployeeSupplierRepository employeeSupplierRepository;
    EmployeeRepository employeeRepository;
    SupplierRepository supplierRepository;

    @Override
    public EmployeeSupplierResponse create(EmployeeSupplierRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        EmployeeSupplierId id = new EmployeeSupplierId(employee.getEmployeeId(), supplier.getSupplierId());

        if (employeeSupplierRepository.existsById(id)) {
            throw new AppException(ErrorCode.ENTITY_EXISTED);
        }

        EmployeeSupplier employeeSupplier = EmployeeSupplier
                .builder()
                .employeeSupplierId(id)
                .employee(employee)
                .supplier(supplier)
                .bookingStatus(request.getBookingStatus())
                .orderTime(request.getOrderTime())
                .totalOrderAmount(request.getTotalOrderAmount())
                .build();

        employeeSupplierRepository.save(employeeSupplier);

        return employeeSupplierMapper.toResponse(employeeSupplier);
    }

    @Override
    public List<EmployeeSupplierResponse> searchByEmployeeId(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        List<EmployeeSupplier> employeeSuppliers = employeeSupplierRepository.findByEmployee_EmployeeId(employeeId);

        if (employeeSuppliers.isEmpty()) {
            throw new AppException(ErrorCode.NO_RELATION_FOUND);
        }

        return employeeSuppliers
                .stream()
                .map(employeeSupplierMapper::toResponse)
                .toList();
    }

    @Override
    public List<EmployeeSupplierResponse> searchBySupplierId(Long supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        List<EmployeeSupplier> employeeSuppliers = employeeSupplierRepository.findBySupplier_SupplierId(supplierId);

        if (employeeSuppliers.isEmpty()) {
            throw new AppException(ErrorCode.NO_RELATION_FOUND);
        }

        return employeeSuppliers
                .stream()
                .map(employeeSupplierMapper::toResponse)
                .toList();
    }
}
