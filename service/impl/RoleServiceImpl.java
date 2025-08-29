package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.response.RoleWithPermissionsResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Role;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.mapper.RoleMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.RoleRepository;
import com.huynguyen.bbqrestaurantmanagement.service.RoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl extends GenericServiceImpl<Role, String> implements RoleService {
    public RoleServiceImpl(JpaRepository<Role, String> jpaRepository, RoleRepository roleRepository, RoleMapper roleMapper) {
        super(jpaRepository);
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public RoleWithPermissionsResponse getRoleDetails(String roleName) {
        Role role = roleRepository.findById(roleName)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleWithPermissionsResponse> findAllRolesWithPermissions() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toRoleResponseList(roles);
    }
}
