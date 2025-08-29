package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.entity.Permission;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController extends GenericController<Permission, String>{

    protected PermissionController(GenericService<Permission, String> genericService) {
        super(genericService);
    }
}
