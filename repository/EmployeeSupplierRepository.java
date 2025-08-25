package com.huynguyen.bbqrestaurantmanagement.repository;

import com.huynguyen.bbqrestaurantmanagement.dto.response.EmployeeSupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.EmployeeSupplier;
import com.huynguyen.bbqrestaurantmanagement.keys.EmployeeSupplierId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSupplierRepository extends JpaRepository<EmployeeSupplier, EmployeeSupplierId> {

    List<EmployeeSupplier> findByEmployee_EmployeeId(Long employeeId);
    List<EmployeeSupplier> findBySupplier_SupplierId(Long supplierId);
}
