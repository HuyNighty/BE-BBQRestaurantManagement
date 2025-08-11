package com.huynguyen.bbqrestaurantmanagement.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "customer_id")
    String customerId;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "phone_number", unique = true)
    String phoneNumber;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "address")
    String address;

    @Column(name = "loyalty_points")
    Integer loyaltyPoints;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Booking> bookings;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    User user;
}
