package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class GenericController<T, ID extends Serializable> {

    protected final GenericService<T, ID> genericService;

    protected GenericController(GenericService<T, ID> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ApiResponse<List<T>> getAll() {
        return ApiResponse.
                <List<T>>builder()
                .result(genericService.findAll())
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<T> create(@Validated @RequestBody T entity) {
        return ApiResponse.
                <T>builder()
                .result(genericService.save(entity))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<T> getById(@PathVariable ID id) {
        return ApiResponse.
                <T>builder()
                .result(genericService.findById(id).orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND)))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<T> update(@PathVariable ID id, @RequestBody T entity) {
        return ApiResponse.<T>builder()
                .result(genericService.update(id, entity))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> delete(@PathVariable ID id) {
        genericService.deleteById(id);

        return ApiResponse.
                builder()
                .result("Deleted successfully")
                .build();
    }
}
