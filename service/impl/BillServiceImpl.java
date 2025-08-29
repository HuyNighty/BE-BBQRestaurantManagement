package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.dto.response.BillResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Bill;
import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.mapper.BillMapper;
import com.huynguyen.bbqrestaurantmanagement.repository.BillRepository;
import com.huynguyen.bbqrestaurantmanagement.service.BillService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillServiceImpl extends GenericServiceImpl<Bill, Long> implements BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public BillServiceImpl(JpaRepository<Bill, Long> jpaRepository,
                           BillRepository billRepository, BillMapper billMapper) {
        super(jpaRepository);
        this.billRepository = billRepository;
        this.billMapper = billMapper;
    }

    @Override
    public List<BillResponse> findByPaymentTimeBetween(LocalDateTime from, LocalDateTime to) {
        if (from == null || to == null || from.isAfter(to)) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        List<Bill> bills = billRepository.findByPaymentTimeBetween(from, to);
        return bills
                .stream()
                .map(billMapper::toResponse)
                .toList();
    }

    @Override
    public List<BillResponse> findByGrandTotalAmountGreaterThanEqual(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        List<Bill> bills = billRepository.findByGrandTotalAmountGreaterThanEqual(amount);
        return bills
                .stream()
                .map(billMapper::toResponse)
                .toList();
    }
}
