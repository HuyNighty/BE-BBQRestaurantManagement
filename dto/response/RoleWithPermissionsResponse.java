package com.huynguyen.bbqrestaurantmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleWithPermissionsResponse {

    String roleName;
    String description;
    List<PermissionInRoleResponse> permissions;
}
