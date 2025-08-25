package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.request.RegisterRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RegisterResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Customer;
import com.huynguyen.bbqrestaurantmanagement.entity.Role;
import com.huynguyen.bbqrestaurantmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    // Map RegisterRequest → User (chỉ map userName, không map password & roles vì xử lý riêng)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toUser(RegisterRequest request);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "loyaltyPoints", constant = "0")
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    Customer toCustomer(RegisterRequest request);

    @Mapping(source = "user.userName", target = "userName")
    @Mapping(source = "customer.email", target = "email")
    @Mapping(expression = "java(customer.getFirstName() + \" \" + customer.getLastName())", target = "fullName")
    RegisterResponse toRegisterResponse(User user, Customer customer);
}
