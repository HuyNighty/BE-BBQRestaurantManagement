package com.huynguyen.bbqrestaurantmanagement.service;

import com.huynguyen.bbqrestaurantmanagement.dto.response.BillResponse;
import com.huynguyen.bbqrestaurantmanagement.entity.Bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface BillService extends GenericService<Bill, Long> {

    List<BillResponse> findByPaymentTimeBetween(LocalDateTime from, LocalDateTime to);

    List<BillResponse> findByGrandTotalAmountGreaterThanEqual(BigDecimal amount);
}
