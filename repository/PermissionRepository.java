package com.huynguyen.bbqrestaurantmanagement.repository;

import com.huynguyen.bbqrestaurantmanagement.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String>{
}
