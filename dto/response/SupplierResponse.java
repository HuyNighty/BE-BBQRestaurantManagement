package com.huynguyen.bbqrestaurantmanagement.dto.response;

import com.huynguyen.bbqrestaurantmanagement.entity.IngredientSupplier;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierResponse {

    Long supplierId;
    String supplierName;
    String contactPerson;
    String phoneNumber;
    String email;
    String address;
}
