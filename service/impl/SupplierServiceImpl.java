package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.request.SupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.SupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.SupplierWithIngredientsAndEmployeesResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Supplier;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCodeDetail;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.mapper.SupplierMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.SupplierRepository;
import com.huynguyen.bbqrestaurantmanagement.service.SupplierService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl extends GenericServiceImpl<Supplier, Long> implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(JpaRepository<Supplier, Long> jpaRepository, SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        super(jpaRepository);
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }


    @Override
    public List<SupplierWithIngredientsAndEmployeesResponse> searchSupplierByContactPerson(String personName) {

        List<Supplier> suppliers = supplierRepository.findByContactPersonContainingIgnoreCase(personName);

        if (suppliers.isEmpty()) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return supplierMapper.toResponseList(suppliers);
    }

    @Override
    public List<SupplierWithIngredientsAndEmployeesResponse> getAllSuppliersWithDetails() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return supplierMapper.toResponseList(suppliers);
    }

    @Override
    public List<SupplierWithIngredientsAndEmployeesResponse> searchSupplierBySupplierName(String keyword) {

        List<Supplier> suppliers = supplierRepository.findBySupplierNameContainingIgnoreCase(keyword);

        if (suppliers.isEmpty()) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return supplierMapper.toResponseList(suppliers);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        if (supplier.getIngredientSuppliers() != null) {
            supplier.getIngredientSuppliers().clear();
        }

        supplierRepository.delete(supplier);
    }

    @Override
    public SupplierResponse create(SupplierRequest request) {
        List<ErrorCodeDetail> validationErrors = validateSupplierRequest(request);

        if (!validationErrors.isEmpty()) {
            throw new AppException(ErrorCode.VALIDATION_FAILED, validationErrors);
        }

        Supplier supplier = Supplier
                .builder()
                .supplierName(request.getSupplierName())
                .contactPerson(request.getContactPerson())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .build();

        supplierRepository.save(supplier);

        return supplierMapper.toSupplierResponse(supplier);
    }

    private List<ErrorCodeDetail> validateSupplierRequest(SupplierRequest request) {
        List<ErrorCodeDetail> errors = new ArrayList<>();

        if (supplierRepository.existsBySupplierName(request.getSupplierName())) {
            errors.add(buildErrorCodeDetail(ErrorCode.USER_EXISTED));
        }

        if (supplierRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            errors.add(buildErrorCodeDetail(ErrorCode.PHONE_NUMBER_EXISTED));
        }

        if (supplierRepository.existsByEmail(request.getEmail())) {
            errors.add(buildErrorCodeDetail(ErrorCode.EMAIL_EXISTED));
        }

        return errors;
    }

    private ErrorCodeDetail buildErrorCodeDetail(ErrorCode errorCode) {
        return ErrorCodeDetail
                .builder()
                .code(errorCode.getCode())
                .massage(errorCode.getMassage())
                .build();
    }
}
