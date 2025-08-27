package com.huynguyen.bbqrestaurantmanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {

    @NotBlank
    String supplierName;

    String contactPerson;

    @Pattern(regexp = "^[0-9]{10,11}$", message = "Phone number must be 10 to 11 digits and contain only numbers")
    @NotBlank
    String phoneNumber;

    @NotBlank
    @Email
    String email;

    String address;

}
