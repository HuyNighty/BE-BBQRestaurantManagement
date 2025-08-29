package com.huynguyen.bbqrestaurantmanagement.controller;

import com.huynguyen.bbqrestaurantmanagement.entity.PaymentMethod;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController extends GenericController<PaymentMethod, Long> {
    protected PaymentMethodController(GenericService<PaymentMethod, Long> genericService) {
        super(genericService);
    }
}
