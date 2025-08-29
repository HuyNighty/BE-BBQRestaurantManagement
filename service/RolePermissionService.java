package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.request.RolePermissionRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RolePermissionResponse;

public interface RolePermissionService {

    RolePermissionResponse addByRole(String roleName, RolePermissionRequest request);
}
