package com.huynguyen.bbqrestaurantmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {

    Long reviewId;
    String fullName;
    String email;
    String menuItemName;

    Integer rating;
    String comment;
    LocalDateTime reviewTime;
}
