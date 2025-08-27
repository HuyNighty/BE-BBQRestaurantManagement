package com.huynguyen.bbqrestaurantmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LoginResponse {

    boolean authenticated;
    String userName;
    String accessToken;
    Set<String> roles;
}
