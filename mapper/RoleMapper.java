package com.huynguyen.bbqrestaurantmanagement.mapper;

import com.huynguyen.bbqrestaurantmanagement.dto.response.PermissionInRoleResponse;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RoleWithPermissionsResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Role;
import com.huynguyen.bbqrestaurantmanagement.entity.RolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "rolePermissions", target = "permissions", qualifiedByName = "mapToPermissionIntoRoleResponse")
    RoleWithPermissionsResponse toRoleResponse(Role role);

    @Named("mapToPermissionIntoRoleResponse")
    default List<PermissionInRoleResponse> mapToPermissionIntoRoleResponse(Set<RolePermission> rolePermissions) {
        return rolePermissions
                .stream()
                .map(rolePermission -> PermissionInRoleResponse
                        .builder()
                        .permissionName(rolePermission.getPermission().getPermissionName())
                        .permissionDescription(rolePermission.getPermission().getDescription())
                        .build())
                .toList();
    }

    List<RoleWithPermissionsResponse> toRoleResponseList(List<Role> roles);
}
