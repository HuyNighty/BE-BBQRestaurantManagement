package com.huynguyen.bbqrestaurantmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolePermissionResponse {

    String roleName;
    String roleDescription;

    String permissionName;
    String permissionDescription;
}
