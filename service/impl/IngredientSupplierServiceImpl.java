package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.request.IngredientSupplierRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.IngredientSupplierResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Ingredient;
import com.huynguyen.bbqrestaurantmanagement.entity.IngredientSupplier;
import com.huynguyen.bbqrestaurantmanagement.entity.Supplier;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.keys.IngredientSupplierId;
import com.huynguyen.bbqrestaurantmanagement.mapper.IngredientSupplierMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.IngredientRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.IngredientSupplierRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.SupplierRepository;
import com.huynguyen.bbqrestaurantmanagement.service.IngredientSupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientSupplierServiceImpl implements IngredientSupplierService {

    IngredientRepository ingredientRepository;
    SupplierRepository supplierRepository;
    IngredientSupplierRepository ingredientSupplierRepository;
    IngredientSupplierMapper ingredientSupplierMapper;

    @Override
    public IngredientSupplierResponse create(IngredientSupplierRequest request) {
        Ingredient ingredient = ingredientRepository.findById(request.getIngredientId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        IngredientSupplierId id = new IngredientSupplierId(
                ingredient.getIngredientId(),
                supplier.getSupplierId()
        );

        boolean exists = ingredientSupplierRepository.existsById(id);
        if (exists) {
            throw new AppException(ErrorCode.ENTITY_EXISTED);
        }

        IngredientSupplier entity = IngredientSupplier
                .builder()
                .ingredientSupplierId(id)
                .ingredient(ingredient)
                .supplier(supplier)
                .price(request.getPrice())
                .deliveryTimeEstimate(request.getDeliveryTimeEstimate())
                .build();

        IngredientSupplier saved = ingredientSupplierRepository.save(entity);
        return ingredientSupplierMapper.toResponse(saved);
    }

    @Override
    public List<IngredientSupplierResponse> getAllBySupplier(Long supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        List<IngredientSupplier> list = ingredientSupplierRepository.findBySupplier_SupplierId(supplierId);

        if (list.isEmpty()) {
            throw new AppException(ErrorCode.NO_RELATION_FOUND);
        }

        return list
                .stream()
                .map(ingredientSupplierMapper::toResponse)
                .toList();
    }

    @Override
    public List<IngredientSupplierResponse> getAllByIngredient(Long ingredientId) {

        if (!ingredientRepository.existsById(ingredientId)) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        List<IngredientSupplier> list = ingredientSupplierRepository.findByIngredient_ingredientId(ingredientId);

        if (list.isEmpty()) {
            throw new AppException(ErrorCode.NO_RELATION_FOUND);
        }
        return list
                .stream()
                .map(ingredientSupplierMapper::toResponse)
                .toList();
    }
}
