package com.huynguyen.bbqrestaurantmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MyInfoResponse {

    String userName;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String address;
    Integer loyaltyPoints;
}
