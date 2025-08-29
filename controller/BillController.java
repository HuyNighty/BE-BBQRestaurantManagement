package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.BillRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.BillResponse;
import com.huynguyen.bbqrestaurantmanagement.mapper.BillMapper;
import com.huynguyen.bbqrestaurantmanagement.service.BillService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/bills")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class BillController {

    BillService billService;
    BillMapper billMapper;

    @PostMapping
    public ApiResponse<BillResponse> create(@RequestBody BillRequest request) {
        return ApiResponse
                .<BillResponse>builder()
                .result(billMapper.toResponse(billService.save(billMapper.toEntity(request))))
                .build();
    }
}
