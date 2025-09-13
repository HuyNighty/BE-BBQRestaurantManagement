package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.ReviewRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.ReviewTimeRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.ReviewUpdateRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ReviewResponse;
import com.huynguyen.bbqrestaurantmanagement.service.ReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewController {

    ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ApiResponse<ReviewResponse> add(@RequestBody ReviewRequest request) {
        return ApiResponse
                .<ReviewResponse>builder()
                .result(reviewService.add(request))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<ReviewResponse>> findAll() {
        return ApiResponse
                .<List<ReviewResponse>>builder()
                .result(reviewService.findAll())
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<ReviewResponse> findById(@PathVariable Long id) {
        return ApiResponse
                .<ReviewResponse>builder()
                .result(reviewService.findById(id))
                .build();
    }

    @GetMapping("/rating/{rating}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<ReviewResponse>> findAllByRating(@PathVariable Integer rating) {
        return ApiResponse
                .<List<ReviewResponse>>builder()
                .result(reviewService.findAllByRating(rating))
                .build();
    }

    @PostMapping("/search-by-time")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<ReviewResponse>> findByTime(@RequestBody ReviewTimeRequest request) {
        return ApiResponse
                .<List<ReviewResponse>>builder()
                .result(reviewService.findAllByReviewTime(request))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ApiResponse<ReviewResponse> update(@PathVariable Long id,
                                                 @RequestBody ReviewUpdateRequest request) {
        return ApiResponse
                .<ReviewResponse>builder()
                .result(reviewService.updateReview(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        reviewService.deleteById(id);
        return ApiResponse
                .<Void>builder()
                .code(1000)
                .message("Deleted successfully!")
                .build();
    }
}

