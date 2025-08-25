package com.huynguyen.bbqrestaurantmanagement.repository;

import com.huynguyen.bbqrestaurantmanagement.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findBySupplierNameContainingIgnoreCase(String keyword);
    List<Supplier> findByContactPersonContainingIgnoreCase(String personName);
    boolean existsBySupplierName(String supplierName);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
}
