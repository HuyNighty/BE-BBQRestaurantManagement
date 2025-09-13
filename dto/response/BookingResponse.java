package com.huynguyen.bbqrestaurantmanagement.dto.response;

import com.huynguyen.bbqrestaurantmanagement.enums.BookingStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {

    Long bookingId;
    String customerId;
    String fullName;
    String phoneNumber;
    Long tableId;
    String tableName;
    LocalDateTime bookingDateTime;
    Integer numberOfGuests;
    BookingStatus bookingStatus;
}
