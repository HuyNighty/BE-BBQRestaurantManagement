package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.MyInfoResponse;

import com.huynguyen.bbqrestaurantmanagement.dto.response.MyInfoSource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyInfoMapper {

    @Mapping(source = "user.userName", target = "userName")
    @Mapping(source = "customer.firstName", target = "firstName")
    @Mapping(source = "customer.lastName", target = "lastName")
    @Mapping(source = "customer.phoneNumber", target = "phoneNumber")
    @Mapping(source = "customer.address", target = "address")
    @Mapping(source = "customer.email", target = "email")
    @Mapping(source = "customer.loyaltyPoints", target = "loyaltyPoints")
    MyInfoResponse toMyInfoResponse(MyInfoSource source);

    List<MyInfoResponse> tMyInfoResponseList(List<MyInfoSource> sources);
}
