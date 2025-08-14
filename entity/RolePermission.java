package com.huynguyen.bbqrestaurantmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.huynguyen.bbqrestaurantmanagement.keys.RolePermissionId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "role_permissions")
public class RolePermission {

    @EmbeddedId
    RolePermissionId id;

    @ManyToOne
    @MapsId("roleName")
    @JoinColumn(name = "role_name", nullable = false, updatable = false)
    @JsonBackReference("role-ref")
    Role role;

    @ManyToOne
    @MapsId("permissionName")
    @JoinColumn(name = "permission_name", nullable = false, updatable = false)
    @JsonBackReference("permission-ref")
    Permission permission;
}
