package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.entity.PaymentMethod;
import com.huynguyen.bbqrestaurantmanagement.service.PaymentMethodService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl extends GenericServiceImpl<PaymentMethod, Long> implements PaymentMethodService {
    public PaymentMethodServiceImpl(JpaRepository<PaymentMethod, Long> jpaRepository) {
        super(jpaRepository);
    }
}
