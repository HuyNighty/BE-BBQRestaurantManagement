package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.entity.RestaurantTable;
import com.huynguyen.bbqrestaurantmanagement.service.RestaurantTableService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantTableServiceImpl extends GenericServiceImpl<RestaurantTable, Long> implements RestaurantTableService {
    public RestaurantTableServiceImpl(JpaRepository<RestaurantTable, Long> jpaRepository) {
        super(jpaRepository);
    }
}
