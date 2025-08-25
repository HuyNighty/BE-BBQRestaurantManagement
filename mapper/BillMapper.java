package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.request.BillRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.BillResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Bill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill toEntity(BillRequest request);

    BillResponse toResponse(Bill bill);

    List<BillResponse> toResponseList(List<Bill> bills);
}
