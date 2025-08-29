package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.entity.RestaurantTable;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant-tables")
@PreAuthorize("hasRole('ADMIN')")
public class RestaurantTableController extends GenericController<RestaurantTable, Long> {

    protected RestaurantTableController(GenericService<RestaurantTable, Long> genericService) {
        super(genericService);
    }
}
