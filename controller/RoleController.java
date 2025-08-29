package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.dto.request.RolePermissionRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.ApiResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RolePermissionResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RoleWithPermissionsResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Role;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;
import com.huynguyen.bbqrestaurantmanagement.service.RolePermissionService;
import com.huynguyen.bbqrestaurantmanagement.service.RoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController extends GenericController<Role, String> {

    RolePermissionService rolePermissionService;
    RoleService roleService;

    protected RoleController(GenericService<Role, String> genericService, RolePermissionService rolePermissionService, RoleService roleService) {
        super(genericService);
        this.rolePermissionService = rolePermissionService;
        this.roleService = roleService;
    }

    @PostMapping("/{roleName}/role-permissions")
    public ApiResponse<RolePermissionResponse> addByRole(
            @PathVariable String roleName,
            @RequestBody RolePermissionRequest request) {
        return ApiResponse
                .<RolePermissionResponse>builder()
                .result(rolePermissionService.addByRole(roleName, request))
                .build();
    }

    @GetMapping("/{roleName}/details")
    public ApiResponse<RoleWithPermissionsResponse> getRole(@PathVariable String roleName) {
        return ApiResponse.<RoleWithPermissionsResponse>builder()
                .result(roleService.getRoleDetails(roleName))
                .build();
    }

    @GetMapping("/with-permissions")
    public ApiResponse<List<RoleWithPermissionsResponse>> getAllRolesWithPermissions() {

        return ApiResponse
                .<List<RoleWithPermissionsResponse>>builder()
                .result(roleService.findAllRolesWithPermissions())
                .build();
    }
}
