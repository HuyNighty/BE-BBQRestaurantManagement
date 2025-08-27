package com.huynguyen.bbqrestaurantmanagement.dto.response;

import com.huynguyen.bbqrestaurantmanagement.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {
    String userName;
    String fullName;
    String email;
    Set<Role> roles;
}
