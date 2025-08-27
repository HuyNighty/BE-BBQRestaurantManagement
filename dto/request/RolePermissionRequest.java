package com.huynguyen.bbqrestaurantmanagement.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermissionRequest {

    String roleName;
    String permissionName;
    String permissionDescription;
}
