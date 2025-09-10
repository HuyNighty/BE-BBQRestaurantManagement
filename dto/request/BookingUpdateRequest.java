package com.huynguyen.bbqrestaurantmanagement.dto.request;

import com.huynguyen.bbqrestaurantmanagement.enums.BookingStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingUpdateRequest {

    String customerId;
    Long tableId;
    LocalDate bookingDate;
    LocalTime bookingTime;
    Integer numberOfGuests;
    BookingStatus bookingStatus;
}
