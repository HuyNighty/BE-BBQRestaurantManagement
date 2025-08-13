package com.huynguyen.bbqrestaurantmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huynguyen.bbqrestaurantmanagement.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    @JsonIgnore
    RestaurantTable restaurantTable;

    @Column(name = "booking_time", nullable = false)
    LocalDateTime bookingTime;

    @Column(name = "number_of_guests", nullable = false)
    Integer numberOfGuests;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    BookingStatus status;
}
