package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.response.RoleWithPermissionsResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Role;

import java.util.List;

public interface RoleService extends GenericService<Role, String> {
    RoleWithPermissionsResponse getRoleDetails(String roleName);
    List<RoleWithPermissionsResponse> findAllRolesWithPermissions();
}
