package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.entity.Permission;
import com.huynguyen.bbqrestaurantmanagement.service.PermissionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, String> implements PermissionService {

    public PermissionServiceImpl(JpaRepository<Permission, String> jpaRepository) {
        super(jpaRepository);
    }
}
