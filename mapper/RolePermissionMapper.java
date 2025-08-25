package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.RolePermissionResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.RolePermission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolePermissionMapper {

    default RolePermissionResponse toResponse(RolePermission rolePermission) {
        return RolePermissionResponse
                .builder()
                .roleName(rolePermission.getRole().getRoleName())
                .roleDescription(rolePermission.getRole().getDescription())
                .permissionName(rolePermission.getPermission().getPermissionName())
                .permissionDescription(rolePermission.getPermission().getDescription())
                .build();
    }
}
