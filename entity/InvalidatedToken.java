package com.huynguyen.bbqrestaurantmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "invalidated_tokens")
public class InvalidatedToken {

    @Id
    String id; // Token id (jti)

    Date expiryTime;
}
