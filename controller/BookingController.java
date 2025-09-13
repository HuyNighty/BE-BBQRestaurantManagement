package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.BookingRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.BookingUpdateRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.BookingResponse;
import com.huynguyen.bbqrestaurantmanagement.enums.BookingStatus;
import com.huynguyen.bbqrestaurantmanagement.service.BookingService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RequestMapping("api/bookings")
public class BookingController {

    BookingService bookingService;

    @PostMapping
    public ApiResponse<BookingResponse> create(@RequestBody BookingRequest bookingRequest) {
        return ApiResponse
                .<BookingResponse>builder()
                .result(bookingService.add(bookingRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BookingResponse>> getAll() {
        return ApiResponse
                .<List<BookingResponse>>builder()
                .result(bookingService.findAll())
                .build();
    }

    @GetMapping("/{bookingId}")
    public ApiResponse<BookingResponse> getByBookingId(@PathVariable Long bookingId) {
        return ApiResponse
                .<BookingResponse>builder()
                .result(bookingService.findById(bookingId))
                .build();
    }

    @PutMapping("/{bookingId}")
    public ApiResponse<BookingResponse> update(@PathVariable Long bookingId, @RequestBody BookingUpdateRequest bookingUpdateRequest) {
        return ApiResponse
                .<BookingResponse>builder()
                .result(bookingService.update(bookingId, bookingUpdateRequest))
                .build();
    }

    @DeleteMapping("/{bookingId}")
    public ApiResponse<Void> delete(@PathVariable Long bookingId) {
        return ApiResponse
                .<Void>builder()
                .code(1000)
                .message("Deleted Successfully!")
                .build();
    }

    @GetMapping("/findByPhoneNumber/{phoneNumber}")
    public ApiResponse<BookingResponse> findByPhoneNumber(@PathVariable String phoneNumber) {
        return ApiResponse
                .<BookingResponse>builder()
                .result(bookingService.findByPhoneNumber(phoneNumber))
                .build();
    }

    @GetMapping("/findByFullName/{fullName}")
    public ApiResponse<BookingResponse> findByFullName(@PathVariable String fullName) {
        return ApiResponse
                .<BookingResponse>builder()
                .result(bookingService.findByFullName(fullName))
                .build();
    }

    @GetMapping("/findByStatus/{status}")
    public ApiResponse<List<BookingResponse>> findByStatus(@PathVariable BookingStatus status) {
        return ApiResponse
                .<List<BookingResponse>>builder()
                .result(bookingService.findByBookingStatus(status))
                .build();
    }

    @GetMapping("/findByTableName/{tableName}")
    public ApiResponse<BookingResponse> findByTableName(@PathVariable String tableName) {
        return ApiResponse
                .<BookingResponse>builder()
                .result(bookingService.findByTableName(tableName))
                .build();
    }
}

