package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.request.BookingRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.request.BookingUpdateRequest;
import com.huynguyen.bbqrestaurantmanagement.dto.response.BookingResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Booking;
import com.huynguyen.bbqrestaurantmanagement.entity.Customer;
import com.huynguyen.bbqrestaurantmanagement.entity.RestaurantTable;
import com.huynguyen.bbqrestaurantmanagement.enums.BookingStatus;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.mapper.BookingMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.BookingRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.CustomerRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.RestaurantTableRepository;
import com.huynguyen.bbqrestaurantmanagement.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    BookingMapper  bookingMapper;

    BookingRepository bookingRepository;
    CustomerRepository customerRepository;
    RestaurantTableRepository restaurantTableRepository;

    @Override
    public BookingResponse add(BookingRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        RestaurantTable restaurantTable = restaurantTableRepository.findById(request.getRestaurantTableId())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        LocalDateTime bookingDateTime =  LocalDateTime.of(
                request.getBookingDate(),
                request.getBookingTime()
        );

        Booking booking = Booking
                .builder()
                .customer(customer)
                .restaurantTable(restaurantTable)
                .bookingTime(bookingDateTime)
                .status(request.getStatus())
                .numberOfGuests(request.getNumberOfGuests())
                .build();

        bookingRepository.save(booking);

        return bookingMapper.toResponse(booking);
    }

    @Override
    public List<BookingResponse> findAll() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toResponse)
                .toList();
    }

    @Override
    public BookingResponse findById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        return bookingMapper.toResponse(booking);
    }

    @Override
    public BookingResponse update(Long bookingId, BookingUpdateRequest request) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        LocalDate currentDate = booking.getBookingTime().toLocalDate();
        LocalTime currentTime = booking.getBookingTime().toLocalTime();

        if (request.getBookingDate() != null) {
            currentDate = request.getBookingDate();
        }
        if (request.getBookingTime() != null) {
            currentTime = request.getBookingTime();
        }

        booking.setBookingTime(LocalDateTime.of(currentDate, currentTime));

        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
            booking.setCustomer(customer);
        }

        if (request.getTableId() != null) {
            RestaurantTable restaurantTable = restaurantTableRepository.findById(request.getTableId())
                    .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
            booking.setRestaurantTable(restaurantTable);
        }

        if (request.getNumberOfGuests() != null) {
            booking.setNumberOfGuests(request.getNumberOfGuests());
        }

        if (request.getBookingStatus() != null) {
            booking.setStatus(request.getBookingStatus());
        }

        bookingRepository.save(booking);

        return bookingMapper.toResponse(booking);

    }

    @Override
    public void delete(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        bookingRepository.delete(booking);
    }


    @Override
    public BookingResponse findByPhoneNumber(String phoneNumber) {
        Booking booking = bookingRepository.findByCustomer_PhoneNumber(phoneNumber)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return bookingMapper.toResponse(booking);
    }

    @Override
    public BookingResponse findByFullName(String fullName) {
        Booking booking = bookingRepository.findByCustomerFullName(fullName)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return bookingMapper.toResponse(booking);
    }

    @Override
    public List<BookingResponse> findByBookingStatus(BookingStatus bookingStatus) {
        return bookingRepository.findByStatus(bookingStatus)
                .stream()
                .findFirst()
                .map(bookingMapper::toResponse)
                .stream().toList();
    }

    @Override
    public BookingResponse findByTableName(String tableName) {
        Booking booking = bookingRepository.findByRestaurantTable_TableName(tableName)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return bookingMapper.toResponse(booking);
    }
}
