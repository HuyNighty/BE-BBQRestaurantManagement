package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.request.RolePermissionRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.RolePermissionResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Permission;
import com.huynguyen.bbqrestaurantmanagement.entity.Role;
import com.huynguyen.bbqrestaurantmanagement.entity.RolePermission;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.keys.RolePermissionId;
import com.huynguyen.bbqrestaurantmanagement.mapper.RolePermissionMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.PermissionRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.RolePermissionRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.RoleRepository;
import com.huynguyen.bbqrestaurantmanagement.service.RolePermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RolePermissionServiceImpl implements RolePermissionService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RolePermissionRepository rolePermissionRepository;
    RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional
    public RolePermissionResponse addByRole(String roleName, RolePermissionRequest request) {
        Role role = roleRepository.findById(roleName).orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        Permission permission = permissionRepository.findById(request.getPermissionName()).orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        RolePermissionId id = new RolePermissionId(role.getRoleName(), permission.getPermissionName());
        if (rolePermissionRepository.existsById(id)) {
            throw new RuntimeException("Permission already exists in role");
        }

        RolePermission rolePermission = RolePermission.builder()
                .id(id)
                .role(role)
                .permission(permission)
                .build();

        rolePermissionRepository.save(rolePermission);

        return rolePermissionMapper.toResponse(rolePermission);
    }
}
